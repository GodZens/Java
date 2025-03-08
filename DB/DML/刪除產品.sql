CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_product`(
	IN p_SN VARCHAR(255)
)
BEGIN
	DELETE FROM likelist WHERE SN = p_SN;
    DELETE FROM product WHERE No = p_SN;
END