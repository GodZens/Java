CREATE DEFINER=`root`@`localhost` PROCEDURE `add_user`(    
	IN p_user_id VARCHAR(255),
    IN p_user_name VARCHAR(255),
    IN p_email VARCHAR(255),
    IN p_account VARCHAR(255)
)
BEGIN
    INSERT INTO users (UserID, UserName, Email, Account)
    VALUES (p_user_id, p_user_name, p_email, p_account);
END