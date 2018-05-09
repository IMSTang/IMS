
DROP PROCEDURE IF EXISTS sp_insert_inv_out;
DELIMITER ;;
CREATE  PROCEDURE sp_insert_inv_out(
IN  item_code  varchar(100) ,
IN  inventory_sn     int(11)   ,
IN  batch     varchar(100)  ,
IN  warehouse varchar(100)  ,
IN  position  varchar(100) ,
IN  quantity   double(16,3) ,
IN	irradiation     varchar(100)   ,
IN  tpc     varchar(100)    ,
IN  vendor_id     int(11)   ,
IN  customer_id    int(11)   ,
IN  stockout_date   varchar(10),
IN  create_by   varchar(64),
IN  remark 		  varchar(500)
)

BEGIN

INSERT INTO inv_inventory_out( item_code , inventory_sn , batch , warehouse , position  , quantity  , irradiation , tpc ,
                                vendor_id , customer_id , status ,stockout_date , create_time, create_by , update_by , update_time , remark )
              VALUES ( item_code , inventory_sn , batch , warehouse , position , quantity ,	irradiation , tpc ,
                        vendor_id , customer_id ,	0 ,	stockout_date , sysdate() , create_by , create_by , sysdate() , remark );

END
;;
DELIMITER ;


DROP PROCEDURE IF EXISTS sp_stockOut;
DELIMITER ;;
CREATE  PROCEDURE sp_stockOut(
IN  inventory_sn int(11),
IN  item_code  varchar(100) ,
IN  batch     varchar(100)  ,
IN  warehouse varchar(100)  ,
IN  position  varchar(100) ,
IN  var_quantity   double(16,3) ,
IN	irradiation     varchar(100)   ,
IN  tpc     varchar(100)    ,
IN  vendor_id     int(11)   ,
IN  customer_id    int(11)   ,
IN  stockout_date   varchar(10),
IN  create_by   varchar(64),
IN  remark 		  varchar(500)
)

BEGIN

declare res_quantity int default 0;

declare inventory_quantity INT default 0;

select  quantity into inventory_quantity from inv_inventory where sn=inventory_sn;

set res_quantity =inventory_quantity - var_quantity;

start transaction;

update inv_inventory  set quantity=res_quantity   where sn =inventory_sn;

call sp_insert_inv_out(item_code, inventory_sn, batch, warehouse, position ,
                      var_quantity ,irradiation ,tpc , vendor_id , customer_id , stockout_date , create_by , remark 	 	);


commit;

END
;;
DELIMITER ;



DROP PROCEDURE IF EXISTS sp_stockOutRemove;
DELIMITER ;;
CREATE  PROCEDURE sp_stockOutRemove(
IN  var_sn  int(11)
)

BEGIN

declare tmp_inventory_sn int(11);
declare tmp_quantity double(16,3);
declare res_quantity double(16,3) default 0;
declare inventory_quantity double(16,3) default 0;

select inventory_sn, quantity into tmp_inventory_sn, tmp_quantity from inv_inventory_out where sn=var_sn;
select  quantity into inventory_quantity from inv_inventory where sn=tmp_inventory_sn;

set res_quantity =inventory_quantity + tmp_quantity;

start transaction;

update inv_inventory_out  set status=1 where sn=var_sn;
update inv_inventory  set quantity=res_quantity  where sn=tmp_inventory_sn;

commit;

END
;;
DELIMITER ;

