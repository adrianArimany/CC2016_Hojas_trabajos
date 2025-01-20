package com.example.Estados.Liquadora;

import java.util.Map;
import java.util.Scanner;

public interface  Iliquadora {
    public Map<String, Float> addToLiquiadora(String materialName, Float materialQuantity, Scanner scanner);
    public String increaseVelocity();
    public String decreaseVelocity();
    public String emptyLiquiadora();
}
