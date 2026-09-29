public class FoodResult {
    private FoodStatus status;
    private Food food;

    public FoodResult(FoodStatus status, Food food){
        this.status = status;
        this.food = food;
    }

    public FoodStatus getStatus(){
        return status;
    }

    public Food getFood() {
        return food;
    }

    public int getEffect(){
        return food.healthPoints;
    }
}
