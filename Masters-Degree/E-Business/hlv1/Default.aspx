<%@ Page Title="Welcome" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="hlv1._Default" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <%-- jQuery --%>
    <script src="js/jquery.js"></script>
    <%-- Bootstrap Core JavaScript --%>
    <script src="js/bootstrap.min.js"></script>
    <%--Page Content--%>
    <%--Image Carousel--%>
    <div class="row carousel-holder">
        <div class="col-md-12">
            <div id="carousel" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carousel" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel" data-slide-to="1"></li>
                    <li data-target="#carousel" data-slide-to="2"></li>
                </ol>
                <%--Images in carousel--%>
                <div class="carousel-inner">
                    <div class="item active">
                        <img class="slide-image" src="/Images/Banners/Banner1.png" />
                    </div>
                    <div class="item">
                        <img class="slide-image" src="/Images/Banners/Banner2.png" />
                    </div>
                    <div class="item">
                        <img class="slide-image" src="/Images/Banners/Banner3.png" />
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%-- Product Items --%>
    <div class="row">
        <%--Left Product--%>
        <div class="col-sm-4 col-lg-4 col-md-4">
            <div class="thumbnail">
                <img src="/Images/Products/Product1.png" />
                <div class="caption">
                    <h4 class="pull-right">£134.95</h4>
                    <h4><a href="ProductDetails.aspx?productID=23">Mammut Monolith GTX</a></h4>
                    <p>This Apline Trekking Boot is a light and comfortable new addition</p>
                </div>
            </div>
        </div>
        <%--Middle Product--%>
        <div class="col-sm-4 col-lg-4 col-md-4">
            <div class="thumbnail">
                <img src="/Images/Products/Product2.png" />
                <div class="caption">
                    <h4 class="pull-right">£26.00</h4>
                    <h4><a href="ProductDetails.aspx?productID=11">8b+ Pam</a></h4>
                    <p>Chalk bag 8b+ Pam which contains brush compartment and looks incredible</p>
                </div>
            </div>
        </div>
        <%--Right Product--%>
        <div class="col-sm-4 col-lg-4 col-md-4">
            <div class="thumbnail">
                <img src="/Images/Products/Product3.png" />
                <div class="caption">
                    <h4><a href="ProductList?id=9">Spring Selection</a></h4>
                    <p>With the new season bring new products. Check out the new selection of climbing shoes</p>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
