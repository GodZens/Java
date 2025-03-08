CREATE DEFINER=`root`@`localhost` PROCEDURE `edit_product`(
	IN p_SN VARCHAR(255),
	IN p_ProductName VARCHAR(255),
	IN p_Price DECIMAL(10,2),
    IN p_FeeRate DECIMAL(10,2),
    IN p_Account VARCHAR(255),
    IN p_OrderName INT
)
BEGIN
    -- 更新 Product 表格
    UPDATE product
    SET ProductName = p_ProductName,
        Price = p_Price,
        FeeRate = p_FeeRate
    WHERE No = p_SN; 
    
     -- 更新 likelist 表格
	UPDATE likelist
    SET OrderName = p_OrderName,
		Account = p_Account,
		TotalFee = p_Price*p_FeeRate*p_OrderName,
		TotalAmount = (p_Price*p_OrderName) + p_Price*p_FeeRate*p_OrderName
	WHERE SN = p_SN;
END