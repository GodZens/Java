package financialproducts.products.Service;

import org.springframework.stereotype.Service;
import financialproducts.products.Repository.LikeListRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LikeListService {
    @Autowired
    private final LikeListRepository likeListRepository;

    public LikeListService(LikeListRepository likeListRepository) {
        this.likeListRepository = likeListRepository;
    }

    public void addLikeList(String productName, Integer price, Double feerate, String account, Integer ordername) {
        likeListRepository.addLikeList(productName, price, feerate, account, ordername);
    }

    public void editLikeList(String LikelistNo,String productName,Integer price,Double feerate,String account,Integer ordername) {
        likeListRepository.editLikeList(LikelistNo, productName, price, feerate, account, ordername);
    }

    public void deleteLikeList(String LikelistNo) {
        likeListRepository.deleteLikeList(LikelistNo);
    }
}