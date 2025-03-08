CREATE DEFINER=`root`@`localhost` PROCEDURE `get_product`(
	IN limitSize INT, 
	IN offsetSize INT
)
BEGIN
    -- 查詢符合條件的分頁資料，並使用 JOIN 連接三個表
    SELECT  l.SN, p.ProductName, l.Account as PreAccount, l.TotalAmount, l.TotalFee,
			u.Account AS Account, u.Email
    FROM likelist as l
    left join product as p ON p.No = l.SN
    left join users as u ON l.Account = u.Account
    LIMIT limitSize OFFSET offsetSize;
    
    -- 查詢總筆數
    SELECT COUNT(*) AS total FROM product;
END