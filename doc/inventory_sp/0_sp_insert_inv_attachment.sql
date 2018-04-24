DROP PROCEDURE IF EXISTS sp_insert_inv_attachment;
DELIMITER ;;
CREATE  PROCEDURE sp_insert_inv_attachment(
IN  attachment_name  varchar(100) ,
IN  attachment     varchar(100)  ,
IN  inventory_sn int(11)  ,
IN  inventory_type  varchar(100) ,
IN  create_by   varchar(64),
IN  remark 		  varchar(500) 	
)

BEGIN

INSERT INTO inv_attchment
(	
  attachment_name  ,
  attachment  ,
  inventory_sn ,
  inventory_type ,
  create_by   ,
  create_time  ,
  update_by   ,
	update_time ,
  remark 		 
 )
VALUES (
  attachment_name  ,
  attachment  ,
  inventory_sn ,
  inventory_type ,
  create_by   ,
  SYSDATE()  ,
  update_by   ,
	SYSDATE(),
  remark 		
);
END
;;
DELIMITER ;
