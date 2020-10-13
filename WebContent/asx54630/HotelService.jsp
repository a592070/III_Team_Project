<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>找旅館</title> 
   <script src="${pageContext.servletContext.contextPath}/static/jquery-3.5.1.js"></script>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/fragment/header.jsp" />
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
                    <select id="inputState" class="form-control" placeholder="地區" name="regionkeywd">
                        <option value="">請選擇..</option>
                        <option value="基隆市">基隆市</option>
                        <option value="新北市">新北市</option>
                        <option value="臺北市">臺北市</option>
                        <option value="桃園市">桃園市</option>
                        <option value="新竹市">新竹市</option>
                        <option value="新竹縣">新竹縣</option>
                        <option value="苗栗縣">苗栗縣</option>
                        <option value="宜蘭縣">宜蘭縣</option>
                        <option value="臺中市">臺中市</option>
                        <option value="彰化縣">彰化縣</option>
                        <option value="南投縣">南投縣</option>
                        <option value="雲林縣">雲林縣</option>
                        <option value="嘉義市">嘉義市</option>
                        <option value="嘉義縣">嘉義縣</option>
                        <option value="臺南市">臺南市</option>
                        <option value="高雄市">高雄市</option>
                        <option value="屏東縣">屏東縣</option>
                        <option value="花蓮縣">花蓮縣</option>
                        <option value="臺東縣">臺東縣</option>
                        <option value="澎湖縣">澎湖縣</option>
                        <option value="金門縣">金門縣</option>
                        <option value="連江縣">連江縣</option>
                    </select>
                  </div>
                  <div class="form-group col-md-2">
                    <label for="inputState">類型:</label>
                    <select id="inputState" class="form-control" placeholder="住宿類型" name="typekeywd">
                      <option value="">請選擇..</option>
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
                <td><button type='button' class='btn btn-info'  onclick="clickdetail(晶華酒店)" >更多資訊</button></td>
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal" >我要訂房</button></td>
            </tr>
            <tr>
                <td>日月光國際大飯店</td>
                <td>新竹市東區中央路355巷16號1,3-6樓</td>
                <td>飯店</td>
                <td>5</td>
                <td><button type='button' class='btn btn-info'  onclick="clickdetail(日月光國際大飯店)" >更多資訊</button></td>
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal">我要訂房</button></td>
            </tr>
            <tr>
                <td>約客頂級汽車旅館</td>
                <td>桃園市桃園區泰山街58號1-4樓</td>
                <td>汽車旅館</td>
                <td>4.6</td>
                <td><button type='button' class='btn btn-info'  onclick="clickdetail(約客頂級汽車旅館)" >更多資訊</button></td>
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal">我要訂房</button></td>
            </tr>
            <tr>
                <td>榮興金鬱金香酒店</td>
                <td>臺南市中西區民族路二段128號1至9樓</td>
                <td>4.5</td>
                <td>飯店</td>
                <td><button type='button' class='btn btn-info'  onclick="clickdetail(榮興金鬱金香酒店)" >更多資訊</button></td>
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal">我要訂房</button></td>
            </tr>
            </tbody>
        </table>


		<script type="text/javascript">
		function clickdetail(sn){
			
			document.location.href="${pageContext.servletContext.contextPath}/HotelDetailServlet?detailsn="+sn;
		}
		
		</script>
		
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

