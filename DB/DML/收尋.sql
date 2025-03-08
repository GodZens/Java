CREATE DEFINER=`root`@`localhost` PROCEDURE `search`(
    IN limitSize INT,               -- 每頁顯示的產品數量
    IN offsetSize INT,              -- 分頁的偏移量
    IN p_productname VARCHAR(255)   -- 用於產品名稱過濾
)
BEGIN
    -- 查詢符合條件的分頁資料，並使用 JOIN 連接三個表
    SELECT  l.SN, p.ProductName, l.Account as PreAccount, l.TotalAmount, l.TotalFee,
            u.Account AS Account, u.Email
    FROM likelist AS l
    LEFT JOIN product AS p ON p.No = l.SN
    LEFT JOIN users AS u ON l.Account = u.Account
    WHERE p.ProductName LIKE CONCAT('%', p_productname, '%')
    LIMIT limitSize OFFSET offsetSize;
    
    -- 查詢符合條件的總筆數
    SELECT COUNT(*) AS total 
    FROM likelist AS l
    LEFT JOIN product AS p ON p.No = l.SN
    LEFT JOIN users AS u ON l.Account = u.Account
    WHERE p.ProductName LIKE CONCAT('%', p_productname, '%');
END