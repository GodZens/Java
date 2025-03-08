CREATE DEFINER=`root`@`localhost` PROCEDURE `get_product_information`(
	IN p_productNo VARCHAR(255)
)
BEGIN
    -- 查詢符合條件的分頁資料，並使用 JOIN 連接三個表
    SELECT  l.SN, l.Account,l.OrderName , p.ProductName, p.Price, p.FeeRate
    FROM likelist as l
	Join product as p ON p.No = l.SN
    Where l.SN = p_productNo;
    
END