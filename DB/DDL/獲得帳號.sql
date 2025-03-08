CREATE DEFINER=`root`@`localhost` PROCEDURE `get_account`()
BEGIN
    -- 獲得所有帳號
    SELECT Account FROM users;
END