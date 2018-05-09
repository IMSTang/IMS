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
declare tmp_in_quantity double(16,3);
declare tmp_inventory_quantity double(16,3);
declare tmp_new_quantity double(16,3);
declare inv_sn int;

select item_code , batch , warehouse , position , quantity into tmp_item_code, tmp_batch, tmp_warehouse, tmp_position , tmp_in_quantity from inv_inventory_in where sn=var_sn ;

select max(i.sn) into inv_sn
    from inv_inventory i where i.status=0 and i.item_code=item_code and i.batch=batch and i.warehouse=warehouse and i.position=position;

select i.quantity into tmp_inventory_quantity from inv_inventory i where i.sn=inv_sn ;

set tmp_new_quantity = tmp_inventory_quantity - tmp_in_quantity ;

IF(tmp_new_quantity < 0) then

  select '-100' into var_result from dual;

ELSE
  start transaction;

  update inv_inventory_in  set status=1 where sn=var_sn;

  update inv_inventory i set i.quantity = tmp_new_quantity where i.sn=inv_sn ;

  commit;

  select '1' into var_result from dual;

END IF;

END
;;
DELIMITER ;

