DROP PROCEDURE IF EXISTS sp_insert_inv_in;
DELIMITER ;;
CREATE  PROCEDURE sp_insert_inv_in(
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
IN  create_by   varchar(64),
IN  remark 		  varchar(500),
OUT  out_new_sn     int(11)
)

BEGIN

INSERT INTO inv_inventory_in
(	item_code, batch, warehouse, position, price_purchase, price_fob_ontario, quantity, irradiation, tpc, vendor_id, status, stock_in_date,
  create_time, create_by, update_by,	update_time, remark )
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
	0      ,
	sysdate() ,
  sysdate() ,
  create_by,
  create_by   ,
	sysdate() ,
  remark
);

set out_new_sn=LAST_INSERT_ID();

END
;;
DELIMITER ;







DROP PROCEDURE IF EXISTS sp_insert_inv;
DELIMITER ;;
CREATE  PROCEDURE sp_insert_inv(
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
IN  create_by   varchar(64) ,
IN  remark 		  varchar(500) ,
OUT  out_new_sn     int(11)
)

BEGIN

INSERT INTO inv_inventory
(	item_code, batch, warehouse, position, price_purchase, price_fob_ontario, quantity, irradiation, tpc, vendor_id, customer_id, status ,stock_in_date ,
  create_time, create_by, update_by,	update_time, remark )
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

set out_new_sn=LAST_INSERT_ID();

END
;;
DELIMITER ;



DROP PROCEDURE IF EXISTS sp_stockIn;
DELIMITER ;;
CREATE  PROCEDURE sp_stockIn(
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
IN  create_by   varchar(64),
IN  attachment_name  varchar(100) ,
IN  attachment     varchar(100)  ,
IN  remark 		  varchar(500),
OUT var_result VARCHAR(10)
)

BEGIN

declare sn_inv_in INT default 0;
declare sn_inv INT default 0;
start transaction;

call sp_insert_inv_in(item_code, batch, warehouse, position,price_purchase, price_fob_ontario, quantity,irradiation ,tpc, vendor_id,create_by, remark, sn_inv_in);

-- select max(sn) into sn1 from inv_inventory_in;
-- SELECT concat('inv_inventory_in max id is --- ', sn_inv_in);

call sp_insert_inv_attachment(attachment_name, attachment, sn_inv_in, "INV_IN", create_by, remark);

call sp_insert_inv(item_code, batch, warehouse, position, price_purchase, price_fob_ontario, quantity, irradiation, tpc, vendor_id, null, create_by, remark, sn_inv);

-- select max(sn) into sn2 from inv_inventory;
-- SELECT concat('inv_inventory max id is :::: ', sn_inv);

call sp_insert_inv_attachment(attachment_name,attachment,sn_inv_in,"INV",create_by, remark);

commit;

  select '1' into var_result from dual;

END
;;
DELIMITER ;

