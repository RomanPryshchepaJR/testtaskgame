<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>JavaRush Internship</title>
    <link href="data:image/x-icon;base64,AAABAAEAEBAAAAEAIABoBAAAFgAAACgAAAAQAAAAIAAAAAEAIAAAAAAAAAQAABILAAASCwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAuYO8ALmPxAC5h8B4uYfCLLmDw4S5g8OMuYfCPLmHwISxi8QAvYPAAAAAAAAAAAAAAAAAAAAAAAC1f8QAwZfEAMGTxDC9k8FwvY/DLL2Pw/C9i8P8tYfD/LmLw/S9j8M8vZPBhMGTxDjBk8QAvYvEAAAAAADFo8QAxaPEDMWfxPzBm8bEwZvH3MGbx/y9l8f80aPH/Un7z/zls8v8wZfH/MGbx+DBm8bYxZ/FDMWjxBDFo8QA0bvEBMmrxZzFp8ewxafH/MWnx/zFp8f85bvH/o7v4/93m/f9UgvP/L2fx/zFp8f8xafH/MWnx7jJq8W8zbPECM23xJzNs8dozbPH/M2zx/zNs8f8vavH/apPz//v8/v+yyPr/NG3x/zJs8f8zbPH/M2zx/zNs8f8zbPHgM23xLTRv8UY0b/HyNG/x/zRv8f80b/H/MGzx/32i9P//////nLn4/zBs8f80b/H/NG/x/zRv8f80b/H/NG/x9jRv8U01cvJHNXLy8zVy8v81cvL/NXLy/zFw8v+Hq/X//////5a1+P8ycPL/NXLy/zVy8v81cvL/NXLy/zVy8vY1cvJONnXyRzZ18vM2dfL/NnXy/zV08v9TiPP/2+b8/97o/f9YjPT/NXTy/zZ18v82dfL/NnXy/zZ18v82dfL2NnXyTjh48kc4ePLzOHjy/zh48v82d/L/VIvy/9vm+v/e6P3/WY/0/zZ38v84ePL/OHjy/zh48v84ePL/OHjy9jh48k45fPJHOXzy8zl88v85fPL/OXzy/zV58v+JsPT//////5e6+P81efL/OXzy/zl88v85fPL/OXzy/zl88vY5fPJOOn/zRjp/8/I6f/P/On/z/zp/8/83ffP/gaz1//////+fwPn/N3zz/zp/8/86f/P/On/z/zp/8/86f/P2On/zTTuB8yc8gvPaPILz/zyC8/88gvP/OIDz/3Cj9P/7/P7/tc/7/z2D8/87gvP/PILz/zyC8/88gvP/PILz4DuB8y06fvMBPYTzZz2F8+w9hfP/PYXz/zyF8/9EifL/qMf2/9/q/P9dmfX/O4Tz/z2F8/89hfP/PYXz7j2E8288gfMCPYfzAD2G8wM+h/M/PojzsT6I8/c+iPP/PYjz/0GK8/9dm/P/Ro30/z6I8/8+iPP4Pojztj6H80M9hvMEPYbzAAAAAAA/jPMAP4n0AD+J9Aw/ivRcP4v0y0CL9Pw/i/T/Por0/z+L9P0/i/TPP4r0YT+J9A4/ivQAPorzAAAAAAAAAAAAAAAAAAAAAABBjfQAP430AECN9B5AjvSLQY704UGO9ONAjvSPQI30IT6O9ABBjfQAAAAAAAAAAAAAAAAA+B8AAOAHAACAAQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIABAADgBwAA+B8AAA==" rel="icon" type="image/x-icon" />
    <meta id="root" about="${pageContext.request.contextPath}">
    <link href="${pageContext.request.contextPath}/resources/bootstrap-4.3.1-dist/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap-4.3.1-dist/js/jq.js" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-4.3.1-dist/js/jq.js">
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-4.3.1-dist/js/bootstrap.js">
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts.js">
    </script>

</head>
<body onload="loadContent('${pageContext.request.contextPath}','', 1);">
<div class="container">


            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Error!</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="alert alert-danger" role="alert" id="error-text">
                                This is a danger alert—check it out!
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>



    <h1 style="text-align: center; margin-top: 50px; margin-bottom: 30px">Admin panel</h1>

        <label style="float: left ; margin-right: 10px" for="order">Order by: </label>
        <select style="float: left" onchange="processSearch('${pageContext.request.contextPath}', 1)" style="margin-left: 5px" id="order"
                class="form-control-sm">
            <option selected>Id</option>
            <option>Name</option>
            <option>Experience</option>
            <option>Birthday</option>
        </select>


        <select style="float: right" onchange="processSearch('${pageContext.request.contextPath}', 1)" style="margin-left: 5px" id="limit"
                class="form-control-sm">
            <option>1</option>
            <option selected>3</option>
            <option>5</option>
            <option>10</option>
            <option>20</option>
        </select>
     <label style="float: right; margin-right: 10px" for="limit">Players in a page: </label>

<br>

    <table style="margin-top: 10px" class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Title</th>
            <th scope="col">Race</th>
            <th scope="col">Profession</th>
            <th scope="col">Experience</th>
            <th scope="col">Level</th>
            <th scope="col">Until Next Level</th>
            <th scope="col">Birthday</th>
            <th scope="col">Banned</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody id="mainTable">
        </tbody>
    </table>
    <h5 id="count" style="float: right; margin-right: 20px"></h5>
    <div>
        <ul id="pagging-bar" class="pagination pagination-sm justify-content-center">

        </ul>
    </div>


    <button style="margin-bottom: 15px" type="button" class="btn btn-info" onclick="clickCreate()">Create new player</button>
    <form style="background-color: #E9ECEF; padding: 20px; border-radius: 10px; display: none" id="createButton">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputNameNew">Name</label>
                <input type="text" class="form-control" id="inputNameNew" placeholder="Name">
            </div>
            <div class="form-group col-md-6">
                <label for="inputTitleNew">Title</label>
                <input type="text" class="form-control" id="inputTitleNew" placeholder="Title">
            </div>
        </div>
        <div class="form-row">

            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label>Birthday</label>
                <div class="form-row">
                        <input type="date" class="form-control" id="inputBirthdayNew">
                </div>
            </div>
            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label>Experience</label>
                <div class="form-row">
                        <input type="number" min="0" step="1000" class="form-control" id="inputExperienceNew">
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label for="inputRace">Race</label>
                <select id="inputRaceNew" class="form-control">
                    <option selected>Human</option>
                    <option>Dwarf</option>
                    <option>Elf</option>
                    <option>Giant</option>
                    <option>Orc</option>
                    <option>Troll</option>
                    <option>Hobbit</option>
                </select>
            </div>
            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label for="inputProfession">Profession</label>
                <select id="inputProfessionNew" class="form-control">
                    <option selected>Warrior</option>
                    <option>Rogue</option>
                    <option>Sorcerer</option>
                    <option>Cleric</option>
                    <option>Paladin</option>
                    <option>Nazgul</option>
                    <option>Warlock</option>
                    <option>Druid</option>
                </select>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6" style="padding: 0px 10px">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadioNew1"
                           value="false" checked="checked">
                    <label class="form-check-label" for="inlineRadio2">Active</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadioNew2"
                           value="true">
                    <label class="form-check-label" for="inlineRadio3">Banned</label>
                </div>
            </div>
        </div>
        <button type="button" onclick="processCreate('${pageContext.request.contextPath}')" class="btn btn-success">
            Create
        </button>
    </form>

    <h3 style="margin-top: 50px">Filter options:</h3>
    <form style="background-color: #E9ECEF; padding: 20px; border-radius: 10px">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputName">Name</label>
                <input type="text" class="form-control" id="inputName" placeholder="Name">
            </div>
            <div class="form-group col-md-6">
                <label for="inputTitle">Title</label>
                <input type="text" class="form-control" id="inputTitle" placeholder="Title">
            </div>
        </div>
        <div class="form-row">

            <div class="form-group col-md-6" style="padding: 0px 10px">
                <label>Birthday between</label>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <input type="date" class="form-control" id="inputBirthdayAfter">
                    </div>
                    <div class="form-group col-md-6">
                        <input type="date" class="form-control" id="inputBirthdayBefore">
                    </div>
                </div>
            </div>
            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label>Experience between</label>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <input type="number" min="0" step="1000" class="form-control" id="inputExperienceMin">
                    </div>
                    <div class="form-group col-md-6">
                        <input type="number" min="0" step="1000" class="form-control" id="inputExperienceMax">
                    </div>
                </div>
            </div>
            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label>Level between</label>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <input type="number" min="0" class="form-control" id="inputLevelMin">
                    </div>
                    <div class="form-group col-md-6">
                        <input type="number" min="0" class="form-control" id="inputLevelMax">
                    </div>
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-3">
                <label for="inputRace">Race</label>
                <select id="inputRace" class="form-control">
                    <option selected>Any</option>
                    <option>Human</option>
                    <option>Dwarf</option>
                    <option>Elf</option>
                    <option>Giant</option>
                    <option>Orc</option>
                    <option>Troll</option>
                    <option>Hobbit</option>
                </select>
            </div>
            <div class="form-group col-md-3">
                <label for="inputProfession">Profession</label>
                <select id="inputProfession" class="form-control">
                    <option selected>Any</option>
                    <option>Warrior</option>
                    <option>Rogue</option>
                    <option>Sorcerer</option>
                    <option>Cleric</option>
                    <option>Paladin</option>
                    <option>Nazgul</option>
                    <option>Warlock</option>
                    <option>Druid</option>
                </select>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6" style="padding: 0px 10px">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1"
                           value="null" checked="checked">
                    <label class="form-check-label" for="inlineRadio1">Any</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2"
                           value="false">
                    <label class="form-check-label" for="inlineRadio2">Active</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3"
                           value="true">
                    <label class="form-check-label" for="inlineRadio3">Banned</label>
                </div>
            </div>
        </div>
        <button type="button" onclick="processSearch('${pageContext.request.contextPath}',1)" class="btn btn-primary">
            Accept
        </button>
    </form>


</div>
</body>
</html>