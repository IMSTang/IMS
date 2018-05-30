DROP FUNCTION IF EXISTS func_get_split_string_total;
CREATE FUNCTION func_get_split_string_total(
f_string varchar(1000),f_delimiter varchar(5)
) RETURNS int(11)
BEGIN
  -- Get the total number of given string.
  return IF(length(f_string)=0,0,1+(length(f_string) - length(replace(f_string,f_delimiter,''))));
END
;;
DELIMITER ;

DROP FUNCTION IF EXISTS func_get_split_string;
CREATE FUNCTION func_get_split_string(
f_string varchar(1000),f_delimiter varchar(5),f_order int) RETURNS varchar(255) CHARSET utf8
BEGIN
  -- Get the separated number of given string.
  declare result varchar(255) default '';
  set result = reverse(substring_index(reverse(substring_index(f_string,f_delimiter,f_order)),f_delimiter,1));
  return result;
END
;;
DELIMITER ;


DROP PROCEDURE IF EXISTS sp_insert_inv_in;
DELIMITER ;;
CREATE  PROCEDURE sp_insert_inv_in(
IN  item_code  varchar(100) ,
IN  batch     varchar(100)  ,
IN  warehouse varchar(100)  ,
IN  position  varchar(100) ,
IN  price_purchase  double(16,2)   ,
IN  price_fob_ontario  double(16,2) ,
IN  var_quantity   double(16,3) ,
IN	irradiation     varchar(100)   ,
IN  tpc     varchar(100)    ,
IN  vendor_id     int(11)   ,
IN  create_by   varchar(64),
IN  remark 		  varchar(500),
OUT  out_new_sn     int(11)
)

BEGIN

INSERT INTO inv_inventory_in(	item_code, batch, warehouse, position, price_purchase, price_fob_ontario, quantity, irradiation, tpc,
                                vendor_id, status, stock_in_date, create_time, create_by, update_by,	update_time, remark )
        VALUES ( item_code , batch , warehouse , position , price_purchase , price_fob_ontario , var_quantity ,	irradiation , tpc ,
                  vendor_id ,	0 , sysdate() , sysdate() , create_by ,  create_by , sysdate() , remark );

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
IN  var_quantity   double(16,3) ,
IN	irradiation     varchar(100)   ,
IN  tpc     varchar(100)    ,
IN  vendor_id     int(11)   ,
IN  customer_id    int(11)   ,
IN  create_by   varchar(64) ,
IN  remark 		  varchar(500) ,
OUT  out_new_sn     int(11)
)

BEGIN

declare inv_sn int;
select max(i.sn) into inv_sn
from inv_inventory i where i.status=0 and i.item_code=item_code and i.batch=batch and i.warehouse=warehouse and i.position=position;
IF (inv_sn is null ) THEN
			INSERT INTO inv_inventory(	item_code, batch, warehouse, position, price_purchase, price_fob_ontario, quantity, irradiation, tpc,
                          vendor_id, customer_id, status ,stock_in_date , create_time, create_by, update_by,	update_time, remark )
        VALUES ( item_code , batch , warehouse , position  , price_purchase , price_fob_ontario , var_quantity , irradiation , tpc ,
                          vendor_id , customer_id ,	0 ,	sysdate() , sysdate() , create_by , create_by ,	sysdate() , remark );

      set out_new_sn=LAST_INSERT_ID();
ELSE
	  update inv_inventory i set i.quantity=i.quantity+var_quantity, i.update_by=create_by, i.update_time=SYSDATE()
        where i.sn=inv_sn;
END IF;


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
IN  quantity   double(16,3) ,
IN	irradiation     varchar(100)   ,
IN  tpc     varchar(100)    ,
IN  vendor_id     int(11)   ,
IN  create_by   varchar(64),
IN  str_attachment_name  varchar(1000) ,
IN  str_attachment_uuid     varchar(1000)  ,
IN  remark 		  varchar(500),
OUT var_result VARCHAR(10)
)

BEGIN

declare sn_inv_in INT default 0;
declare sn_inv INT default 0;

declare cnt int default 0;
declare i int default 0;

start transaction;

call sp_insert_inv_in(item_code, batch, warehouse, position,price_purchase, price_fob_ontario, quantity,irradiation ,tpc, vendor_id,create_by, remark, sn_inv_in);

-- select max(sn) into sn1 from inv_inventory_in;
-- SELECT concat('inv_inventory_in max id is --- ', sn_inv_in);

-- call sp_insert_sys_attachment(attachment_name, attachment, sn_inv_in, "INV_IN", create_by, remark);

call sp_insert_inv(item_code, batch, warehouse, position, price_purchase, price_fob_ontario, quantity, irradiation, tpc, vendor_id, null, create_by, remark, sn_inv);

-- select max(sn) into sn2 from inv_inventory;
-- SELECT concat('inv_inventory max id is :::: ', sn_inv);

-- call sp_insert_sys_attachment(attachment_name, attachment, sn_inv, "INV", create_by, remark);


  set cnt = func_get_split_string_total(str_attachment_uuid,',');

  while i < cnt
  do
    set i = i + 1;
    call sp_insert_sys_attachment(func_get_split_string(str_attachment_name,',',i), func_get_split_string(str_attachment_uuid,',',i), sn_inv_in, "INV_IN", create_by, '');
    call sp_insert_sys_attachment(func_get_split_string(str_attachment_name,',',i), func_get_split_string(str_attachment_uuid,',',i), sn_inv, "INV", create_by, '');
  end while;

commit;

select sn_inv_in into var_result from dual;

END
;;
DELIMITER ;

