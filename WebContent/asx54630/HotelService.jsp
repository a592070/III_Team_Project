<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
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
  <!--------------------------------------------以上為導覽列--------------------------------------------------------->  
  <!--------------------------------------------以下為搜尋列---------------------------------------------------------> 
    <div class="container">
        <h2>飯店</h2>
        <br>
        <div class="mx-auto my-3" style="width: 1100px">
            <form class="form-inline" action="<%=pageContext.getServletContext().getContextPath()%>/HotelController" method="POST">
                <div class="form-group mb-2">
                  <h5>輸入關鍵字:</h5>
                </div>
                <div class="form-group mx-sm-3 mb-2">
                  <label for="inputKeyword" class="sr-only">keyword</label>
                  <input type="text" class="form-control"  name="keyword" placeholder="Search..">
                </div>
                <div class="form-group col-md-2">
                    <label for="inputState">地區:</label>
                    <select id="inputState" class="form-control" placeholder="地區">
                        <option selected>請選擇..</option>
                        <option value="KeelungCity">基隆市</option>
                        <option value="NewTaipeiCity">新北市</option>
                        <option value="TaipeiCity">臺北市</option>
                        <option value="TaoyuanCity">桃園市</option>
                        <option value="HsinchuCity">新竹市</option>
                        <option value="HsinchuCounty">新竹縣</option>
                        <option value="MiaoliCounty">苗栗縣</option>
                        <option value="YilanCounty">宜蘭縣</option>
                        <option value="TaichungCity">臺中市</option>
                        <option value="ChanghuaCounty">彰化縣</option>
                        <option value="NantouCounty">南投縣</option>
                        <option value="YunlinCounty">雲林縣</option>
                        <option value="ChiayiCity">嘉義市</option>
                        <option value="ChiayiCounty">嘉義縣</option>
                        <option value="TainanCity">臺南市</option>
                        <option value="KaohsiungCity">高雄市</option>
                        <option value="PingtungCounty">屏東縣</option>
                        <option value="HualienCounty">花蓮縣</option>
                        <option value="TaitungCounty">臺東縣</option>
                    </select>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputState">類型:</label>
                    <select id="inputState" class="form-control" placeholder="住宿類型">
                      <option selected>請選擇..</option>
                      <option value="飯店">飯店</option>
                      <option value="民宿">民宿</option>
                      <option value="汽車旅館">汽車旅館</option>
                    </select>
                  </div>      
                <button type="submit" class="btn btn-primary mb-2" value="search" name="search">搜尋</button>
              </form>
            </div>
  <!--------------------------------------------以上為搜尋列----------------------------------------------------------->  
  <!--------------------------------------------以下為飯店內容--------------------------------------------------------->
        <br>
        <table class="table">
            <thead>
            <tr>
                <th>名稱</th>
                <th>地址</th>
                <th>住宿類型</th>
                <th>評價</th>
                <th>更多</th>
                <th>功能</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>晶華酒店</td>
                <td>臺北市中山區中山北路2段39巷3號</td>
                <td>飯店</td>
                <td>5.8</td>
                <td><a class="nav-link" href="javascript:void(0)">更多資訊..</a></td>
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal" >我要訂房</button></td>
            </tr>
            <tr>
                <td>花田民宿</td>
                <td>臺中市新社區興中街17-6號</td>
                <td>民宿</td>
                <td>4.4</td>
                <td><a class="nav-link" href="javascript:void(0)">更多資訊..</a></td>
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
                  <h5 class="modal-title" id="exampleModalLabel">預訂飯店:晶華酒店</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form>
                    <div class="form-group">
                      <label for="recipient-name" class="col-form-label">房型:</label><br>
                      <input type="checkbox" value="doubleroom">雙人房
                      <input type="text" >間<br>
                      <input type="checkbox" value="quadrupleroom">四人房
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

