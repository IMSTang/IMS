
DROP PROCEDURE IF EXISTS sp_insert_inv_out;
DELIMITER ;;
CREATE  PROCEDURE sp_insert_inv_out(
IN  item_code  varchar(100) ,
IN  batch     varchar(100)  ,
IN  warehouse varchar(100)  ,
IN  position  varchar(100) ,
IN  price_purchase  double(16,2)   ,
IN  price_fob_ontario  double(16,2) ,
IN  quantity   int(10) ,
IN	irradiation     varchar(100)   ,
IN  tpc     varchar(100)    ,
IN  vendor_id     int(11)   ,
IN  customer_id    int(11)   ,
IN  create_by   varchar(64),
IN  remark 		  varchar(500)
)

BEGIN

INSERT INTO inv_inventory_out
(	item_code , batch     ,warehouse , position  , price_purchase ,price_fob_ontario   , quantity    ,irradiation      , tpc      ,
  vendor_id   ,customer_id   ,status       ,stock_in_date ,
  create_time, create_by,
  update_by,
	update_time,
  remark 		 )
VALUES (
  item_code ,
  batch     ,
  warehouse ,
  position  ,
  price_purchase ,
  price_fob_ontario   ,
  quantity    ,
	irradiation      ,
  tpc      ,
  vendor_id   ,
  customer_id   ,
	0      ,
	sysdate() ,
  sysdate() ,
  create_by,
  create_by   ,
	sysdate() ,
  remark
);
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
IN  price_purchase  double(16,2)   ,
IN  price_fob_ontario  double(16,2) ,
IN  var_quantity   int(10) ,
IN	irradiation     varchar(100)   ,
IN  tpc     varchar(100)    ,
IN  vendor_id     int(11)   ,
IN  customer_id    int(11)   ,
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

call sp_insert_inv_out(item_code, batch, warehouse, position ,price_purchase  , price_fob_ontario ,
var_quantity ,irradiation ,tpc  , vendor_id , customer_id , create_by , remark 	 	);


commit;

END
;;
DELIMITER ;





DROP PROCEDURE IF EXISTS sp_stockOutRemove;
DELIMITER ;;
CREATE  PROCEDURE sp_stockOutRemove(
IN  var_sn  int(100)
)

BEGIN

declare tmp_item_code VARCHAR(100);
declare tmp_batch VARCHAR(100);
declare tmp_warehouse VARCHAR(100);
declare tmp_position VARCHAR(100);
declare tmp_quantity int(10);

declare res_quantity INT default 0;
declare inventory_quantity INT default 0;

select item_code into tmp_item_code  from inv_inventory_out where sn=var_sn;
select batch into tmp_batch from inv_inventory_out where sn=var_sn;
select warehouse into tmp_warehouse  from inv_inventory_out where sn=var_sn;
select position into tmp_position  from inv_inventory_out where sn=var_sn;
select quantity into tmp_quantity  from inv_inventory_out where sn=var_sn;


select  quantity into inventory_quantity from inv_inventory
where item_code=tmp_item_code and batch=tmp_batch  and warehouse=tmp_warehouse and position =tmp_position;

set res_quantity =inventory_quantity + tmp_quantity;


start transaction;

update inv_inventory_out  set status=1 where sn=var_sn;

update inv_inventory  set quantity=res_quantity  where item_code=tmp_item_code and batch=tmp_batch  and warehouse=tmp_warehouse and position =tmp_position;


commit;

END
;;
DELIMITER ;

