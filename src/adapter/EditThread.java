package adapter;

public interface EditThread {
    public void updateOptionSetName(String modelName, String optionSetName, String newName);
    public void updateOptionPrice(String modelName, String optionSetName, String optionName, int newPrice);
    public void updateChoice(String modelName, String optionsName, String optionName);

}
