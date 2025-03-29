package colm;

public class AcсountUser {
    public int userId;// индивидуальный номер пользователя
    public String userPinCode;//пароль пользователя
    public String name; //имя пользователя

    //Констурктор создания пользователя
    public void AccountUser(int userId, String name, String userPinCode){
        this.name = name;
        this.userId = userId;
        this.userPinCode = userPinCode;
    }
    //возвращение айди пользователя
    public int  getUserId(){
        return userId;
    }
    //Проверка на вход в аккаунт
    public boolean authenticate(String inputPassword){
        return userPinCode.equals(inputPassword);
    }
}
