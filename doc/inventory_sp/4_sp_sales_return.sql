
DROP PROCEDURE IF EXISTS sp_insert_sales_return;
DELIMITER ;;
CREATE  PROCEDURE sp_insert_sales_return(
IN  item_code  varchar(100) ,
IN  stockout_sn     int(11)   ,
IN  batch     varchar(100)  ,
IN  warehouse varchar(100)  ,
IN  position  varchar(100) ,
IN  quantity   int(10) ,
IN  po_code  varchar(20) ,
IN  vendor_id     int(11)   ,
IN  customer_id    int(11)   ,
IN  return_date   varchar(10),
IN  create_by   varchar(64),
IN  remark 		  varchar(500)
)

BEGIN

INSERT INTO inv_sales_return ( item_code , stockout_sn , batch , warehouse , position , quantity , po_code ,
                                 vendor_id ,customer_id  ,status ,return_date , create_time, create_by, update_by, update_time, remark )
              VALUES ( item_code , stockout_sn , batch , warehouse , position , quantity , po_code ,
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
IN  var_position  varchar(100) ,
IN  var_quantity   double(16,3),
IN  po_code  varchar(20) ,
IN  vendor_id     int(11)   ,
IN  customer_id    int(11)   ,
IN  return_date   varchar(10),
IN  create_by   varchar(64),
IN  remark 		  varchar(500)
)

BEGIN

declare res_quantity double(16,3);
declare inventory_quantity double(16,3);
declare tmp_inventory_sn INT;
declare max_inventory_sn INT;
declare tmp_item_code varchar(100);
declare tmp_batch varchar(100);
declare tmp_warehouse varchar(100);
declare tmp_position varchar(100);
declare tmp_vendor_id INT default 0;
declare tmp_count int default 0;
declare tmp_irradiation varchar(100);
declare tmp_tpc varchar(100);
declare tmp_price_purchase double(16,2);
declare tmp_price_fob_ontario double(16,2);

select inventory_sn, item_code, batch, warehouse, position, vendor_id
		into tmp_inventory_sn,tmp_item_code,tmp_batch,tmp_warehouse,tmp_position,tmp_vendor_id
		from inv_inventory_out where sn=stockout_sn;

select i.price_purchase , i.price_fob_ontario , i.irradiation , i.tpc
		into tmp_price_purchase,tmp_price_fob_ontario,tmp_irradiation,tmp_tpc
		from inv_inventory i where sn=tmp_inventory_sn;

select max(sn) into max_inventory_sn from inv_inventory i where item_code=tmp_item_code and batch=tmp_batch and warehouse=tmp_warehouse and position=var_position and vendor_id=tmp_vendor_id;

start transaction;

IF (max_inventory_sn is null ) THEN
		INSERT INTO inv_inventory(	item_code, batch, warehouse, position, price_purchase, price_fob_ontario, quantity, irradiation, tpc,
                          vendor_id, customer_id, status ,stock_in_date , create_time, create_by, update_by,	update_time, remark )
    VALUES ( tmp_item_code , tmp_batch , tmp_warehouse , var_position  , tmp_price_purchase , tmp_price_fob_ontario , var_quantity , tmp_irradiation , tmp_tpc ,
                          tmp_vendor_id , null ,	0 ,	sysdate() , sysdate() , create_by , create_by ,	sysdate() , remark );
ELSE
	update inv_inventory i set i.quantity=i.quantity + var_quantity   where sn = max_inventory_sn;
END IF;


call sp_insert_sales_return(item_code, stockout_sn, batch, warehouse, var_position ,
                            var_quantity , po_code , vendor_id , customer_id , return_date , create_by , remark );


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

