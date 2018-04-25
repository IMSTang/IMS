DROP PROCEDURE IF EXISTS sp_stockInRemove;
DELIMITER ;;
CREATE  PROCEDURE sp_stockInRemove(
IN  var_sn  int(100)
)

BEGIN

declare tmp_item_code VARCHAR(100);
declare tmp_batch VARCHAR(100);
declare tmp_warehouse VARCHAR(100);
declare tmp_position VARCHAR(100);

select item_code into tmp_item_code  from inv_inventory_in where sn=var_sn;
select batch into tmp_batch from inv_inventory_in where sn=var_sn;
select warehouse into tmp_warehouse  from inv_inventory_in where sn=var_sn;
select position into tmp_position  from inv_inventory_in where sn=var_sn;

start transaction;

update inv_inventory_in  set status=1 where sn=var_sn;

update inv_inventory set status=1 where item_code=tmp_item_code and batch=tmp_batch  and warehouse=tmp_warehouse and position =tmp_position;


commit;

END
;;
DELIMITER ;

