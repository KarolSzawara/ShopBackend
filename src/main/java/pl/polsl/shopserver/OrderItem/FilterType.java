package pl.polsl.shopserver.OrderItem;

import pl.polsl.shopserver.Exception.EnitityNotFound;

public enum FilterType {
    Without(0),
    SixMonth(1),
    OneYear(2),
    TwoYear(3);
    private final Integer id;
    FilterType(Integer id){
        this.id=id;
    }
    public static FilterType fromId(int id) {
        for (FilterType filterType : FilterType.values()) {
            if (filterType.id == id) {
                return filterType;
            }
        }
        throw new EnitityNotFound("Invalid FilterType id: " + id);
    }
}
