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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/CalendarController")
public class CalendarController extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // HttpSession session = req.getSession();


        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        resp.addHeader("Access-Control-Allow-Origin", "*");

        resp.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type, accept");
        //PrintWriter out = resp.getWriter();

        //선택 구간의 전력량
        String start_date = req.getParameter("start_date");
        String end_date = req.getParameter("end_date");
        String id = "USER6";
        String receivedData = null;

        try {
            receivedData = sendGet(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        DataDTO dto = gson.fromJson(receivedData, DataDTO.class);

        //ArrayList<String> timeArr = dto.getTime();

        Gson data_gson = new Gson();
        GraphDTO data = getNeededDate(dto, start_date, end_date);
        String json = data_gson.toJson(data);
        System.out.println(json);

        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(json);


    }

    protected GraphDTO getNeededDate(DataDTO dto, String start_date, String end_date) {

        int start_index = -1;
        int end_index = -1;


        GraphDTO data = null;
        ArrayList<String> dateArr = dto.getTime();
        ArrayList<String> categories = new ArrayList<>();

        ArrayList<Integer> usageArr = dto.getUsage();
        ArrayList<Integer> usages = new ArrayList<>();
        ArrayList<GraphSeries> series = new ArrayList<>();

        for (int i = 0; i < dateArr.size(); i++) {
            if (doDiffOfDate(dateArr.get(i), start_date) <= 0) {
                start_index = i;
                break;
            }
        }

        for (int i = 0; i < dateArr.size(); i++) {
            if (doDiffOfDate(dateArr.get(dateArr.size() - 1 - i), end_date) >= 0) {
                end_index = dateArr.size() - 1 - i;
                break;
            }
        }


        ArrayList<Integer> changIndex = new ArrayList<>();

        if ((start_index != -1 && end_index != -1) && (end_index - start_index >= 1)) {
            for (int i = start_index; i < end_index; i++) {
                String date1 = dateArr.get(i).substring(8, 10);
                String date2 = dateArr.get(i + 1).substring(8, 10);
                if (!date1.equals(date2)) {

                    changIndex.add(i);
                }
                if (i >= end_index - 1) {
                    String formerDate = dateArr.get(changIndex.size() - 1).substring(8, 10);
                    if (!date1.equals(formerDate)) {
                        changIndex.add(i + 1);
                    }
                }
            }
        }

        System.out.println("start_date : " + start_date);
        System.out.println("end_date : " + end_date);
        System.out.println("start : " + start_index);
        System.out.println("end : " + end_index);

        //인덱스 이용해서 날짜 가져오기
      /*  for (int i = start_index; i <= end_index; i++) {
            //System.out.println(changIndex.get(i));
            System.out.println( dateArr.get(i).substring(0,10));

        }
*/
        //사용량 데이터
        for (int i = 0; i < changIndex.size(); i++) {

            categories.add(dateArr.get(changIndex.get(i)).substring(0, 10));

            usages.add(usageArr.get(changIndex.get(i)) - usageArr.get(getFormerDate(dateArr, changIndex.get(i))));
        }

        series.add(new GraphSeries("사용량", usages));

        data = new GraphDTO(categories, series);
        return data;

    }

    // 두날짜의 차이 구하기
    private long doDiffOfDate(String start, String end) {
        //String start = "2015-04-01";
        //String end = "2015-05-05";
        long diffDays = -1;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date beginDate = formatter.parse(start);
            Date endDate = formatter.parse(end);

            // 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
            long diff = endDate.getTime() - beginDate.getTime();
            diffDays = diff / (24 * 60 * 60 * 1000);

            //System.out.println("날짜차이=" + diffDays);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diffDays;
    }

    public String sendGet(String id) throws Exception {

        String url = "http://192.168.1.2:3000/queryUser?userName=" + id;
        String result = null;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", "test");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        result = response.toString();

        //print result
        //   System.out.println(result);
        return result;
    }

    protected int getFormerDate(ArrayList<String> dateArr, int dateIndex) {
        int result = -1;

        String currentDate = dateArr.get(dateIndex).substring(8, 10);

        while (dateIndex - 1 > 0) {
            String formerDate = dateArr.get(dateIndex - 1).substring(8, 10);
            if (!currentDate.equals(formerDate)) {
                result = dateIndex - 1;
                break;
            }
            dateIndex = dateIndex - 1;
        }

        return result;
    }
}
