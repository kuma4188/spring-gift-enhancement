package gift.dto;

import gift.model.Wishlist;
import java.util.List;
import java.util.stream.Collectors;

public class WishlistDTO {
    private Long id;
    private Long productId;
    private String username;
    private int quantity;
    private String productName;
    private int price;
    private String imageUrl;
    private List<OptionDTO> options;
    private int totalPrice;

    public WishlistDTO() {}

    public WishlistDTO(Long id, Long productId, String username, int quantity, String productName, int price, String imageUrl, List<OptionDTO> options) {
        this.id = id;
        this.productId = productId;
        this.username = username;
        this.quantity = quantity;
        this.productName = productName;
        this.price = price;
        this.imageUrl = imageUrl;
        this.options = options;
        this.totalPrice = calculateTotalPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }

    private int calculateTotalPrice() {
        int optionsTotalPrice = options.stream()
            .mapToInt(option -> option.getPrice() * option.getQuantity())
            .sum();
        return optionsTotalPrice;
    }


    public static WishlistDTO convertToDTO(Wishlist wishlist) {
        List<OptionDTO> optionDTOs = wishlist.getOptions().stream()
            .map(OptionDTO::convertToDTO)
            .collect(Collectors.toList());

        return new WishlistDTO(
            wishlist.getId(),
            wishlist.getProduct().getId(),
            wishlist.getUser().getUsername(),
            wishlist.getQuantity(),
            wishlist.getProduct().getName(),
            wishlist.getPrice(),
            wishlist.getProduct().getImageUrl(),
            optionDTOs
        );
    }
}
