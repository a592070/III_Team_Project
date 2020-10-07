<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>找旅館</title> 
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <header class="">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="javascript:void(0)">Logo</a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb">
                <span class="navbar-toggler-icon"></span>
            </button>
    
    
            <div class="collapse navbar-collapse  nav justify-content-center" id="navb">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)">找旅館</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)">找餐廳</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)">找租車</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)">找景點</a>
                    </li>
    
                    <li class="nav-item">
                        <a class="nav-link disabled" href="javascript:void(0)">Disabled</a>
                    </li>
                </ul>
            </div>
            <form class="form-inline my-3">
                <input class="form-control mr-sm-2" type="text" placeholder="Search"/>
                <button class="btn btn-success my-2 my-sm-0" type="button">Search</button>
            </form>
        </div>
        <div class="btn-group">
            <button type="button" class="btn btn-primary">登陸</button>
            <button type="button" class="btn btn-primary">註冊</button>
            <button type="button" class="btn btn-primary">論壇</button>
        </div>
        </nav>
    </header>
    
    <div class="container">
        <h2>飯店</h2>
        <br>
        <div class="mx-auto my-3" style="width: 1100px">
            <form class="form-inline">
                <div class="form-group mb-2">
                  <h5>輸入關鍵字:</h5>
                </div>
                <div class="form-group mx-sm-3 mb-2">
                  <label for="inputKeyword" class="sr-only">keyword</label>
                  <input type="search" class="form-control" id="inputKeyword" placeholder="Search..">
                </div>
                <div class="form-group col-md-2">
                    <label for="inputState">地區:</label>
                    <select id="inputState" class="form-control" placeholder="地區">
                        <option selected>基隆市</option>
                        <option selected>新北市</option>
                        <option selected>臺北市</option>
                        <option selected>桃園市</option>
                        <option selected>新竹市</option>
                        <option selected>新竹縣</option>
                        <option selected>苗栗縣</option>
                        <option selected>宜蘭縣</option>
                        <option selected>臺中市</option>
                        <option selected>彰化縣</option>
                        <option selected>南投縣</option>
                        <option selected>雲林縣</option>
                        <option selected>嘉義市</option>
                        <option selected>嘉義縣</option>
                        <option selected>臺南市</option>
                        <option selected>高雄市</option>
                        <option selected>屏東縣</option>
                        <option selected>花蓮縣</option>
                        <option selected>臺東縣</option>
                    </select>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputState">類型:</label>
                    <select id="inputState" class="form-control" placeholder="住宿類型">
                      <option selected>飯店</option>
                      <option selected>民宿</option>
                      <option selected>汽車旅館</option>
                    </select>
                  </div>      
                <button type="submit" class="btn btn-primary mb-2">搜尋</button>
              </form>
            </div>
        <br>
        <table class="table">
            <thead>
            <tr>
                <th>名稱</th>
                <th>地址</th>
                <th>簡介</th>
                <th>價格</th>
                <th>評價</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>晶華酒店</td>
                <td>臺北市中山區中山北路2段39巷3號</td>
                <td>台北晶華酒店是國際麗晶酒店集團的旗艦店，也是全台北市最卓越、最受歡迎的國際五星級飯店之一。主要的客層包括國際商旅、觀光客及本地客人，裝潢上強調簡雅大方，服務上則務求精緻細膩，期能提供顧客最高品質的住宿、餐飲享受，成為全台精緻生活的標竿。飯店內共有八間中西美食以及餐廳、提供消費者品味中、西、日式佳餚的極致享受；位於二十樓的沐蘭SPA則擁有世界級的頂級芳香療程；而頂樓溫水游泳池、健身房、以及地下一、二樓的麗晶精品名店街…等休閒設施，更可營造完美的渡假享受！台北晶華酒店卓越的服務與設施,在國際獎項中獲獎連連，更連續兩年獲得Conde Naste Traveler讀者票選為亞洲最佳50大飯店之列，且為臺灣區的第一名。亦是許多世界級政商名流與影視巨星蒞臨台北時的首選下榻飯店。飯店擁有538間客房，間間採光良好、平均坪數居業界之冠，每房皆備有快速的便利的ADSL以及無線接撥和寬頻上網系統，房客可隨時輕鬆悠遊網路世界。除此之外，客房內還貼心的提供「枕頭選單」，共有羽毛枕、空氣枕、羊毛枕、記憶枕...等八款軟硬和材質不同的選擇。結合高科技材質、擁有感溫、塑型、釋壓、舒眠效果的Wellspring臥舒麗床墊，更為房客營造出最舒適的睡眠環境。本飯店位居台北市的中心地帶，緊鄰台北火車站與捷運淡水線中山站4號出口，並鄰近南西/中山百貨商圈，飯店門口也設有機場接駁與各線公車停駁站，連接全台北市各觀光與知名據點。無論是遊覽、購物或商務洽公,皆享最快速方便的交通服務。</td>
                <td>雙人房:5000 四人房:8000</td>
                <td>5.8</td>
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal" >我要訂房</button></td>
            </tr>
            <tr>
                <td>xxx</td>
                <td>xxx</td>
                <td>xxx</td>
                <td>xxx</td>
                <td>xxx</td>
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal">我要訂房</button></td>
            </tr>
            <tr>
                <td>xxx</td>
                <td>xxx</td>
                <td>xxx</td>
                <td>xxx</td>
                <td>xxx</td>
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal">我要訂房</button></td>
            </tr>
            <tr>
                <td>xxx</td>
                <td>xxx</td>
                <td>xxx</td>
                <td>xxx</td>
                <td>xxx</td>
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal">我要訂房</button></td>
            </tr>
            </tbody>
        </table>

        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">預定飯店:晶華酒店</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form>
                    <div class="form-group">
                      <label for="recipient-name" class="col-form-label">房型:</label><br>
                      <input type="checkbox" value="doubleroom">雙人房5000元
                      <input type="text" >間<br>
                      <input type="checkbox" value="quadrupleroom">四人房8000元
                      <input type="text">間<br>
                    </div>
                    <div class="form-group">
                        <label for="" class="t1">入住日期:</label>
                        <input type="date" name="checkin"><br>
                        <label for="" class="t1">退房日期:</label>
                        <input type="date" name="checkout">
                      </div>
                  </form>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                  <button type="submit" class="btn btn-primary">確認</button>
                </div>
              </div>
            </div>
          </div>
    </div>
</body>
</html>