package Controller.user;


import Model.user.CalendarDAO;
import Model.user.DataDTO;
import Model.user.GraphDTO;
import Model.user.GraphSeries;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


//오늘 사용한 전력량
@WebServlet("/TodayEnergyController")
public class TodayEnergyController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  HttpSession session = req.getSession();

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        resp.addHeader("Access-Control-Allow-Origin", "*");

        resp.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type, accept");


        //  String today_date = req.getParameter("today_date");
        String today_date = "2019-05-03";
        CalendarController calendarconn = new CalendarController();
        String id = "USER6";
        String receivedData = null;
        try {
            receivedData = calendarconn.sendGet(id);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Gson gson = new Gson();

        DataDTO dto = gson.fromJson(receivedData, DataDTO.class);

        ArrayList<String> times = new ArrayList<>();
        ArrayList<Integer> usage = new ArrayList<>();

        ArrayList<String> categories = new ArrayList<>();
        ArrayList<Integer> usages = new ArrayList<>();
        ArrayList<Integer> usageData = new ArrayList<>();

        //시간 배열과 각각에 따른 사용량 arrayList 생성
        for (int i = 0; i < dto.getTime().size(); i++) {
            //   System.out.println("dto: " + dto.getTime().get(i));
            if (dto.getTime().get(i).substring(0, 10).equals(today_date)) {

                times.add(dto.getTime().get(i).substring(11, 16));
                usage.add(dto.getUsage().get(i));
            }

        }
        int cnt = 0;
        int sum = 0;
        //시간 동안 사용량
        for (int i = 0; i < times.size() ; i++) {
            sum+=usage.get(i);
            if (i == times.size()-1||!times.get(i).substring(0, 1).equals(times.get(i + 1).substring(0, 1))) {

                usages.add(sum);
                sum=0;
            }

        }

        for(int i=0; i<usages.size();i++){
            System.out.println(usages.get(i));
        }

        //사용량 계산

        for (int i=0; i<usages.size();i++){
            int index=0;
            if(i !=0) {
             usageData.add(usages.get(i) - usages.get(i - 1));
            }else{



                for(int j=0;j<dto.getTime().size();j++) {
                    if (!today_date.equals(dto.getTime().get(j).substring(0, 10))) {
                        index++;
                    }else {
                        break;
                    }
                }
                usageData.add(usages.get(i)-dto.getUsage().get(index-1));
            }
        }


        for(int i=0; i<usageData.size();i++){
            System.out.println("사용량: "+usageData.get(i));
        }

        GraphSeries use = new GraphSeries("사용량", usageData);

        ArrayList<GraphSeries> series = new ArrayList<>();
        series.add(use);

        //categories 아이돈노
        categories.add("00:00");
        categories.add("12:00");

        GraphDTO data = new GraphDTO(categories, series);

        Gson data_gson = new Gson();
        String today_json = data_gson.toJson(data);
        System.out.println(today_json);
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(today_json);
    }


}
