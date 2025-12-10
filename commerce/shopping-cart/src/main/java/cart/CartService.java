package cart.nes;

import interaction_api.cart.model.ChangeProductQuantityRequest;
import interaction_api.cart.model.ShoppingCartDto;
import interaction_api.cart.model.exception.NoProductsInShoppingCartException;
import interaction_api.cart.model.exception.NotAuthorizedUserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository repository;
    private final CartMapper mapper;

    @Transactional(readOnly = true)
    public ShoppingCartDto get(String username) throws NotAuthorizedUserException {

        log.info("Запрос корзины по юзернейму {}", username);

        checkUsername(username);
        Cart cart = getCartByUsername(username);

        return mapper.toDto(cart);
    }

    public ShoppingCartDto add(String username, Map<UUID, Long> products) throws NotAuthorizedUserException {

        log.info("Запрос на добавление товара в корзину");

        checkUsername(username);
        Cart cart = getCartByUsername(username);
        repository.save(cart);
        products.forEach((productId, quantity) ->
                cart.getProducts().merge(productId, quantity, Long::sum)
        );
        log.info("Запрос выполнен успешно. Товаров в корзине: {}", cart.getProducts().size());

        return mapper.toDto(cart);
    }

    public void deleteCart(String username) throws NotAuthorizedUserException {

        log.info("Запрос на удаление корзины");

        checkUsername(username);
        Cart cart = getCartByUsername(username);
        cart.setState(false);

        log.info("Запрос на удаление успешно выполнен");
        log.debug("Корзина пользователя {} была удалена", username);
    }

    public ShoppingCartDto removeProducts(String username, List<UUID> productsIds) throws NotAuthorizedUserException {

        log.info("Запрос на удаление товаров из корзины");

        checkUsername(username);
        Cart cart = getCartByUsername(username);
        for (UUID productId : productsIds) {
            cart.getProducts().remove(productId);
        }

        log.info("Запрос успешно выполнен");

        return mapper.toDto(cart);
    }

    public ShoppingCartDto changeQuantity(String username, ChangeProductQuantityRequest request) throws NotAuthorizedUserException, NoProductsInShoppingCartException {

        log.info("Запрос на изменение количества товара в корзине");

        checkUsername(username);
        Cart cart = getCartByUsername(username);

        if(!cart.getProducts().containsKey(request.getProductId())){
            throw new NoProductsInShoppingCartException("Данный товар не найден в корзине");
        }
        cart.getProducts().put(request.getProductId(), request.getNewQuantity());

        log.info("Количество товара успешно изменено");
        log.debug("Новое количество: {}", request.getNewQuantity());

        return mapper.toDto(cart);
    }

    private void checkUsername(String username) throws NotAuthorizedUserException {
        if (username == null || username.isBlank()) {
            throw new NotAuthorizedUserException("Имя пользователя не может быть пустым");
        }
    }

    private Cart getCartByUsername(String username) {
        return repository.findByUsername(username)
                .orElseGet(() -> new Cart(null, username, true, new HashMap<>()));
    }
}
