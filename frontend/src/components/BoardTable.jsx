import React from "react";
function Header(){
    return(
        <div>
        <table id="board">
            <tr id="title">
                <th id="boardNumber">게시번호</th>
                <th id="title">제목</th>
                <th id="writer">작성자</th>
                <th id="regDate">작성일</th>
                <th id="views">조회 수</th>
            </tr>
            <tr>
                <td>게시물 수</td>
                <td>내용</td>
                <td>ㅎㅇ2</td>
                <td>ㅎㅇ2</td>
                <td>ㅎㅇ2</td>
            </tr>
        </table>
        </div>
    );
}

export default Header;