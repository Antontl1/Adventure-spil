public class FoodResults {
    private FoodResult status;
    private Food food;

    public FoodResults (FoodResult status,Food food){
        this.status=status;
        this.food = food;
    }

    public FoodResult getStatus(){
        return status;
    }

    public Food getFood() {
        return food;
    }
}
