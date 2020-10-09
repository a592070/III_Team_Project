<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/10/10
  Time: 上午 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var currentPage;
    var totalPage;
    var totalCount;
    $(document).ready(selectPage(currentPage));

    function selectPage(nowPage){
        $.get({
            url: "${pageContext.servletContext.contextPath}/TravelSetServlet",
            data:{"method":"optionHotel", "nowPage": nowPage},
            success: function (data) {
                let dataJSON = JSON.parse(data);
                currentPage = dataJSON.currentPage;
                totalPage = dataJSON.totalPage;
                totalCount = dataJSON.totalCount;
                // console.log(dataJSON);

                let paginationContext="";
                paginationContext += "<li class='page-item page-link disabled'>共"+ totalCount +"條紀錄</li>";
                paginationContext += "<li class='page-item page-link disabled'>"+currentPage+"/"+totalPage+"</li>";

                paginationContext += "<li class='page-item  previous'><a class='page-link' href='#' onclick='selectPage(1)'>First</li>";
                paginationContext += "<li class='page-item  previous'><a class='page-link' href='#' onclick='selectPage(currentPage-1)'>Previous</a></li>";

                paginationContext += "<li class='page-item  next' ><a class='page-link' href='#' onclick='selectPage(currentPage+1)'>Next</a></li>";
                paginationContext += "<li class='page-item  next'><a class='page-link' href='#' onclick='selectPage(totalPage)'>Last</a></li>";


                $("#pagination_id").html(paginationContext);


                let list = JSON.parse(dataJSON.currentPageList);
                let context="";
                for (let i = 0; i < list.length; i++) {
                    context += "<tr>"+
                        "<td>" + list[i].name + "</td>" +
                        "<td>" + list[i].doubleRoomPrice + "</td>" +
                        "<td>" + list[i].quadrupleRoomPrice + "</td>" +
                        "<td>" + list[i].address + "</td>" +
                        "<td>" + list[i].rating + "</td>" +
                        "<td><button type='button' class='btn btn-info' onclick='toDetailPage("+list[i].sn+")'>看詳細</button></td>"+
                        "</tr>";
                }
                $("#tbody_id").html(context);

                if(currentPage == 1) {
                    $(".previous").addClass("disabled");
                }else{
                    $(".previous").removeClass("disabled");
                }
                if(currentPage == totalPage) {
                    $(".next").addClass("disabled");
                }else{
                    $(".next").removeClass("disabled");
                }
                $(window).scrollTop(0);

            }
        })
    }

    function jump_to(num){
        var regexp=/^[1-9]\d*$/;
        let totalPageCount = totalPage;
        //alert(totalPageCount);
        if(!regexp.test(num)){
            alert("請輸入大於0的整數！");
            return false;
        }else if((num-totalPageCount) > 0){
            alert("請輸入小於"+totalPageCount+"的整數");
            return false;
        }else{
            selectPage(num);
        }
    }

    function toDetailPage(sn){
        console.log(sn);
    }

    $(document).ready(function(){
        $(".dropdown-toggle").dropdown();
    });
    $(document).ready(function(){
        $("#myInput").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#tbody_id tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
<div class="container">
    <h2>旅館選擇</h2>

    <br>
    <input class="form-control" id="myInput" type="text" placeholder="Search..">
    <br>
    <table class="table">
        <thead>
        <tr>
            <th>旅館名稱</th>
            <th>雙人房價格</th>
            <th>四人房價格</th>
            <th>地址</th>
            <th>評分</th>
        </tr>
        </thead>
        <tbody id="tbody_id">
        </tbody>
    </table>
</div>
<div class="container">
    <form class="form-inline my-2 my-lg-0">
        <input type="text" class="form-control mr-sm-2" placeholder="跳轉頁面" id="inputPage" />
        <button class="btn btn-success my-2 my-sm-0" type="button" onClick='jump_to($("#inputPage").val())'>Go</button>
    </form>
    <ul class="pagination" id="pagination_id">
    </ul>
</div>
