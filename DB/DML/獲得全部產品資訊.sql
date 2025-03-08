CREATE DEFINER=`root`@`localhost` PROCEDURE `get_productList`()
BEGIN
	select distinct ProductName 
    from product;
END