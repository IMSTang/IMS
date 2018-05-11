DROP PROCEDURE IF EXISTS sp_insert_sys_attachment;
DELIMITER ;;
CREATE  PROCEDURE sp_insert_sys_attachment(
IN  attachment_name  varchar(100) ,
IN  attachment_uuid     varchar(100)  ,
IN  main_sn int(11)  ,
IN  main_type  varchar(100) ,
IN  create_by   varchar(64),
IN  remark 		  varchar(500) 	
)

BEGIN

INSERT INTO sys_attachment
(	
  attachment_name  ,
  attachment_uuid  ,
  main_sn ,
  main_type ,
  create_by   ,
  create_time  ,
  update_by   ,
	update_time ,
  remark 		 
 )
VALUES (
  attachment_name  ,
  attachment_uuid  ,
  main_sn ,
  main_type ,
  create_by   ,
  SYSDATE()  ,
  update_by   ,
	SYSDATE(),
  remark 		
);
END
;;
DELIMITER ;
