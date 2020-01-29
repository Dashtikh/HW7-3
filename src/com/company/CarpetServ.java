package com.company;


import java.util.List;


public class CarpetServ {
    private CarpetServ () {
    }

    private static CarpetServ carServ = new CarpetServ ();

    public static CarpetServ getInstance () {
        return carServ;
    }

    public void save (CarpetEnti carEnti) throws Exception {
        try (CarpetRepo carRepo = new CarpetRepo ()) {
            carEnti.setprice (carEnti.getPrice () - (carEnti.getPrice () * 10 / 100));
            carRepo.insert (carEnti);
            carRepo.commit ();
        }
    }
    public void edit (CarpetEnti carEnti) throws Exception{
        try (CarpetRepo carRepo=new CarpetRepo ()){
            carEnti.setprice (carEnti.getPrice ()-(carEnti.getPrice ()*10/100));
            carRepo.update (carEnti);
            carRepo.commit ();
        }
    }
    public void remove(int model) throws Exception{
        try (CarpetRepo carRepo=new CarpetRepo ()){
            carRepo.delete (model);
            carRepo.commit ();
        }
    }
    public List<CarpetEnti> report() throws Exception{
        List<CarpetEnti> carEntis;
        try (CarpetRepo carRepo=new CarpetRepo ()){
            carEntis=carRepo.select ();
        }
        return carEntis;
    }

}
