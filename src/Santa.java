public class Santa {

   private int x = -220, y = 110;
   int count = 1200;

   public void moveForward() {

      
      
      count--;
      if (count < 1){
         count = 1200;
         this.x = -220;
         this.y = 110;
      }else if(count % 3 ==0){
         this.x += 2;
         this.y --;
      }
      
   }

   public void setX(int x) {
      this.x = x;
   }

   public void setY(int y){
      this.y = y;
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }
}