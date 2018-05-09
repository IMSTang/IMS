
DROP PROCEDURE IF EXISTS sp_insert_sales_return;
DELIMITER ;;
CREATE  PROCEDURE sp_insert_sales_return(
IN  item_code  varchar(100) ,
IN  stockout_sn     int(11)   ,
IN  batch     varchar(100)  ,
IN  warehouse varchar(100)  ,
IN  position  varchar(100) ,
IN  quantity   int(10) ,
IN  vendor_id     int(11)   ,
IN  customer_id    int(11)   ,
IN  return_date   varchar(10),
IN  create_by   varchar(64),
IN  remark 		  varchar(500)
)

BEGIN

INSERT INTO inv_sales_return ( item_code , stockout_sn , batch , warehouse , position , quantity ,
                                 vendor_id ,customer_id  ,status ,return_date , create_time, create_by, update_by, update_time, remark )
              VALUES ( item_code , stockout_sn , batch , warehouse , position , quantity ,
                        vendor_id , customer_id ,	0 ,	return_date , sysdate() , create_by , create_by , sysdate() , remark );

END
;;
DELIMITER ;


DROP PROCEDURE IF EXISTS sp_salesReturn;
DELIMITER ;;
CREATE  PROCEDURE sp_salesReturn(
IN  stockout_sn int(11),
IN  item_code  varchar(100) ,
IN  batch     varchar(100)  ,
IN  warehouse varchar(100)  ,
IN  position  varchar(100) ,
IN  var_quantity   int(10) ,
IN  vendor_id     int(11)   ,
IN  customer_id    int(11)   ,
IN  return_date   varchar(10),
IN  create_by   varchar(64),
IN  remark 		  varchar(500)
)

BEGIN

declare res_quantity int default 0;
declare inventory_quantity INT default 0;
declare tmp_inventory_sn INT default 0;

select inventory_sn into tmp_inventory_sn from inv_inventory_out where sn=stockout_sn;
select i.quantity into inventory_quantity from inv_inventory i where sn=tmp_inventory_sn;

set res_quantity = inventory_quantity + var_quantity;

start transaction;

update inv_inventory  set quantity=res_quantity   where sn = tmp_inventory_sn;

call sp_insert_sales_return(item_code, stockout_sn, batch, warehouse, position ,
                            var_quantity , vendor_id , customer_id , return_date , create_by , remark );


commit;

END
;;
DELIMITER ;



DROP PROCEDURE IF EXISTS sp_salesReturnRemove;
DELIMITER ;;
CREATE  PROCEDURE sp_salesReturnRemove(
IN  var_sn  int(11)
)

BEGIN

declare tmp_inventory_sn VARCHAR(100);
declare tmp_stockout_sn VARCHAR(100);
declare tmp_quantity int(10);

declare res_quantity INT default 0;
declare inventory_quantity INT default 0;


select stockout_sn, quantity into tmp_stockout_sn, tmp_quantity from inv_sales_return where sn=var_sn;

select i.sn, i.quantity into tmp_inventory_sn, inventory_quantity from inv_inventory i 
	where sn=(select inventory_sn from inv_inventory_out where sn=tmp_stockout_sn);

set res_quantity = inventory_quantity - tmp_quantity;

start transaction;

update inv_sales_return  set status=1 where sn=var_sn;

update inv_inventory  set quantity=res_quantity  where sn=tmp_inventory_sn;


commit;

END
;;
DELIMITER ;

