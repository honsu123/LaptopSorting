package Testing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

class Laptop {
    private String model;
    private int ramSize;
    private int storageSize;
    private String os;
    private String color;

    public Laptop(String model, int ramSize, int storageSize, String os, String color) {
        this.model = model;
        this.ramSize = ramSize;
        this.storageSize = storageSize;
        this.os = os;
        this.color = color;
    }

    public String getInfo() {
        return model + " - RAM: " + ramSize + "GB, Storage: " + storageSize + "GB, OS: " + os + ", Color: " + color;
    }

    public int getRamSize() {
        return ramSize;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }
}

public class LaptopSort {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Asus", 8, 256, "Windows 10", "Black"));
        laptops.add(new Laptop("HP", 16, 1024, "Windows 11", "Silver"));
        laptops.add(new Laptop("Lenovo", 12, 512, "Windows 10", "Gray"));
        laptops.add(new Laptop("Dell", 8, 1024, "Ubuntu", "Black"));
        laptops.add(new Laptop("Asrock", 16, 512, "Windows 10", "Silver"));
        laptops.add(new Laptop("Gigabyte", 12, 256, "Windows 11", "Gray"));
        laptops.add(new Laptop("Asus", 16, 1024, "Ubuntu", "Black"));
        laptops.add(new Laptop("HP", 8, 512, "Windows 10", "Silver"));
        laptops.add(new Laptop("Lenovo", 12, 256, "Windows 11", "Gray"));
        laptops.add(new Laptop("Dell", 16, 512, "Windows 10", "Silver"));
        Map<String, Object> filterCriteria = getFilterCriteriaFromUser();
        filterLaptops(laptops, filterCriteria);
    }

    public static Map<String, Object> getFilterCriteriaFromUser() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filterCriteria = new HashMap<>();
    
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
    
        System.out.println("Введите номер критерия (от 1 до 4):");
        int criterionNumber = scanner.nextInt();
    
        switch (criterionNumber) {
            case 1:
                System.out.println("Введите требуемый объем ОЗУ (в ГБ):");
                System.out.println("1. 8 GB");
                System.out.println("2. 12 GB");
                System.out.println("3. 16 GB");
                int ramSizeOption = scanner.nextInt();
                int ramSizeCriteria = 0;
                switch (ramSizeOption) {
                    case 1:
                        ramSizeCriteria = 8;
                        break;
                    case 2:
                        ramSizeCriteria = 12;
                        break;
                    case 3:
                        ramSizeCriteria = 16;
                        break;
                    default:
                        System.out.println("Неправильная опция");
                        break;
                }
                filterCriteria.put("RAM", ramSizeCriteria);
                break;
            case 2:
                System.out.println("Введите требуемый объем жесткого диска (в ГБ):");
                System.out.println("1. 256 GB");
                System.out.println("2. 512 GB");
                System.out.println("3. 1024 GB");
                int storageSizeOption = scanner.nextInt();
                int storageSizeCriteria = 0;
                switch (storageSizeOption) {
                    case 1:
                        storageSizeCriteria = 256;
                        break;
                    case 2:
                        storageSizeCriteria = 512;
                        break;
                    case 3:
                        storageSizeCriteria = 1024;
                        break;
                    default:
                        System.out.println("Неправильная опция");
                        break;
                }
                filterCriteria.put("Storage", storageSizeCriteria);
                break;
            case 3:
            System.out.println("Введите требуемую операционную систему:");
            System.out.println("1. Windows 10");
            System.out.println("2. Windows 11");
            System.out.println("3. Ubuntu");
            int osOption = scanner.nextInt();
            String osCriteria = "";
            switch (osOption) {
                case 1:
                    osCriteria = "Windows 10";
                    break;
                case 2:
                    osCriteria = "Windows 11";
                    break;
                case 3:
                    osCriteria = "Ubuntu";
                    break;
                default:
                    System.out.println("Неправильная опция");
                    break;
            }
            filterCriteria.put("OS", osCriteria);
            break;
            case 4:
            System.out.println("Введите требуемый цвет:");
            System.out.println("1. Black");
            System.out.println("2. Silver");
            System.out.println("3. Gray");
            int colorOption = scanner.nextInt();
            String colorCriteria = "";
            switch (colorOption) {
                case 1:
                    colorCriteria = "Black";
                    break;
                case 2:
                    colorCriteria = "Silver";
                    break;
                case 3:
                    colorCriteria = "Gray";
                    break;
                default:
                    System.out.println("Неправильная опция");
                    break;
            }
            filterCriteria.put("Color", colorCriteria);
            break;
        default:
            System.out.println("Неправильный номер критерия");
            break;
        }
    
        scanner.close();
        return filterCriteria;
    }

public static void filterLaptops(Set<Laptop> laptops, Map<String, Object> filterCriteria) {
    List<Laptop> filteredLaptops = new ArrayList<>();
    for (Laptop laptop : laptops) {
        if (filterCriteria.containsKey("RAM") && laptop.getRamSize() < (int) filterCriteria.get("RAM")) {
            continue;
        }
        if (filterCriteria.containsKey("Storage") && laptop.getStorageSize() < (int) filterCriteria.get("Storage")) {
            continue;
        }
        if (filterCriteria.containsKey("OS") && !laptop.getOs().equalsIgnoreCase((String) filterCriteria.get("OS"))) {
            continue;
        }
        if (filterCriteria.containsKey("Color") && !laptop.getColor().equalsIgnoreCase((String) filterCriteria.get("Color"))) {
            continue;
        }
        filteredLaptops.add(laptop);
    }

    Comparator<Laptop> comparator;
    if (filterCriteria.containsKey("RAM")) {
        comparator = Comparator.comparingInt(Laptop::getRamSize);
    } else if (filterCriteria.containsKey("Storage")) {
        comparator = Comparator.comparingInt(Laptop::getStorageSize);
    } else {
        comparator = Comparator.comparing(Laptop::getOs);
    }
    List<Laptop> sortedLaptops = filteredLaptops.stream()
            .sorted(comparator)
            .collect(Collectors.toList());

    // Display the filtered and sorted laptops
    for (Laptop laptop : sortedLaptops) {
        System.out.println(laptop.getInfo());
    }
}
}