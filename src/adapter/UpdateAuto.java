package adapter;

public interface UpdateAuto {
    public void updateOptionSetName(String modelName, String optionSetName, String newName);
    public void updateOptionPrice(String modelName, String optionsName, String optionName, int newPrice);

    public void updateChoice(String modelName, String pptionsName, String optionName);
}
