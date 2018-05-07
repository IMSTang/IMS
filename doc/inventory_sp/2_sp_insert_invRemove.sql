DROP PROCEDURE IF EXISTS sp_stockInRemove;
DELIMITER ;;
CREATE  PROCEDURE sp_stockInRemove(
IN  var_sn  int(11),
OUT var_result VARCHAR(10)
)

BEGIN

declare tmp_item_code VARCHAR(100);
declare tmp_batch VARCHAR(100);
declare tmp_warehouse VARCHAR(100);
declare tmp_position VARCHAR(100);
declare tmp_out int(11);

select item_code into tmp_item_code  from inv_inventory_in where sn=var_sn;
select batch into tmp_batch from inv_inventory_in where sn=var_sn;
select warehouse into tmp_warehouse  from inv_inventory_in where sn=var_sn;
select position into tmp_position  from inv_inventory_in where sn=var_sn;

select count(*) into tmp_out from inv_inventory_out
    where inventory_sn in ( select sn from inv_inventory where item_code=tmp_item_code and batch=tmp_batch  and warehouse=tmp_warehouse and position =tmp_position ) ;

if tmp_out>0 then

  select '-100' into var_result from dual;

else
  start transaction;

  update inv_inventory_in  set status=1 where sn=var_sn;

  update inv_inventory set status=1 where item_code=tmp_item_code and batch=tmp_batch  and warehouse=tmp_warehouse and position =tmp_position;

  commit;

  select '1' into var_result from dual;

end if;

END
;;
DELIMITER ;

