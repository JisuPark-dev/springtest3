package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();
    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item item = new Item("콜라", 1000, 1);
        //when
        Item save = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(item.getId());
        Assertions.assertThat(item).isEqualTo(findItem);
    }

    @Test
    void findAll(){
        //given
        Item item1 = new Item("콜라", 1000, 1);
        Item item2 = new Item("콜라", 1000, 1);
        //when
        itemRepository.save(item1);
        itemRepository.save(item2);
        List<Item> all = itemRepository.findAll();
        //then
        Assertions.assertThat(all.size()).isSameAs(2);
        Assertions.assertThat(all).contains(item1, item2);
    }

    @Test
    void updateItem(){
        //given
        Item item1 = new Item("콜라", 1000, 1);
        Item item2 = new Item("콜라2", 2000, 2);
        itemRepository.save(item1);
        //when
        itemRepository.update(item1.getId(), item2);
        Item findItem = itemRepository.findById(item1.getId());
        //then
        Assertions.assertThat(findItem.getItemName()).isEqualTo("콜라2");
        Assertions.assertThat(findItem.getPrice()).isEqualTo(2000);
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(2);
    }
}