CREATE DEFINER=`root`@`localhost` PROCEDURE `add_likelist`(
	IN p_productname VARCHAR(255),
	IN p_price INT,
    IN p_feerate DECIMAL(10, 2),
    IN p_account VARCHAR(255),
    IN p_ordername INT
)
BEGIN
	DECLARE total_fee DECIMAL(10, 2);  		-- 宣告總手續費變數
    DECLARE total_amount DECIMAL(10, 2);    -- 宣告總金額變數
	SET total_fee = p_price*p_feerate*p_ordername;  -- 計算手續費
    SET total_amount = (p_price*p_ordername) + total_fee;  -- 計算總金額
    
	INSERT INTO product (ProductName, Price, FeeRate)
    VALUES (p_productname, p_price, p_feerate);
    
	INSERT INTO likelist (OrderName, Account, TotalFee, TotalAmount)
    VALUES (p_ordername, p_account, total_fee, total_amount);

END