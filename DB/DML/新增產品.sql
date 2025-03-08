CREATE DEFINER=`root`@`localhost` PROCEDURE `add_product`(
    IN p_productName VARCHAR(255),
    IN p_price INT,
    IN p_feerate DECIMAL(10,2)
)
BEGIN
    DECLARE next_code INT;
    DECLARE new_product_code VARCHAR(255);

    -- 獲取最後一個產品的 product_code
    SELECT MAX(CAST(SUBSTRING(No, 2) AS UNSIGNED)) 
    INTO next_code
    FROM product
    WHERE No LIKE 'A%';

    -- 設置下一個產品編號
    IF next_code IS NULL THEN
        SET next_code = 1;
    ELSE
        SET next_code = next_code + 1;
    END IF;

    -- 生成產品編號
    SET new_product_code = CONCAT('A', LPAD(next_code, 5, '0'));
    
    INSERT INTO product (No, ProductName, Price, FeeRate)
    VALUES (new_product_code, p_productName, p_price, p_feerate);
END