package jpabook.jpashop.service;

import jpabook.jpashop.domain.Item.Book;
import jpabook.jpashop.domain.Item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity, String author, String isbn) {
        //트랜잭션 안에서 엔티티를 조회해야 영속 상태에서 조회된다. 거기에 값을 변경해야 더티 체킹 된다.
        Book findBook = (Book) itemRepository.findOne(itemId);
        //setter를 사용하는 것보다 엔티티 안에서 바로 추적할 수 있는 메서드인 findItem.changeItem()같은 메서드를 호출하도록 바꿔야한다.
        findBook.changeBook(name, price, stockQuantity, author, isbn);

//        findItem.setPrice(param.getPrice());
//        findItem.setName(param.getName());
//        findItem.setStockQuantity(param.getStockQuantity());

//        itemRepository.save(findItem);
    }
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
