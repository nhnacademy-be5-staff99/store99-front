# Thymeleaf Layout Dialect 설명서

## 1. 개요

- **Thymeleaf-layout-dialect**: Thymeleaf 내에 **코드의 재사용성**을 위해서 레이아웃과 재사용이 가능한 템플릿을 구축할 수 있는 오픈소스 라이브러리를 의미합니다.

- Thymeleaf 템플릿 엔진에서 레이아웃을 효과적으로 정의하고 관리할 수 있도록 하는 확장 기능입니다. 이 기술은 **웹 응용 프로그램의 UI 개발 생산성을 높이고, 코드 중복을 줄이며, 유지 보수성을 향상**시키는 데 도움이 됩니다.

## 2. 환경설정

### 1. dependency

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<dependency>
    <groupId>nz.net.ultraq.thymeleaf</groupId>
    <artifactId>thymeleaf-layout-dialect</artifactId>
</dependency>
```

### 2. application.properties

```properties
# thymeleaf
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.cache=false
spring.web.resources.static-locations=classpath:/
```

## 3. 파일구조
![pj_폴더구조](https://github.com/nhnacademy-be5-staff99/store99-front/assets/138862600/cef9a6eb-4d54-4e4c-b45c-85e0524c6f86)


|   파일 명   |  설명                                                   |
|--------------| ----------------------------------------------------------|
|`config.html` | 공통 CSS, font, icon을 초기 파일에 불러올 수 있도록 구성|
|`header.html` | Header 영역의 화면에 대한 목적으로 구성|
|`footer.html` |  Footer 영역의 화면에 대한 목적으로 구성|
|`script.html` |  공통 JS를 초기 파일에 불러올 수 있도록 구성|
|`layout.html` | 구성한 파일들(config, header, footer, script, content)을 엮어주기 위한 목적으로 구성|



| Thymeleaf-Layout 속성 | 설명                                                          |
| ----------------------| --------------------------------------------------------------|
| `xmlns:th="http://www.thymeleaf.org"`| - Thymeleaf Template이라고 선언                |
| `xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"` | - Thymeleaf-layout을 사용함을 선언 |
| `layout:fragment`     | - 해당 속성은 지정한 영역(태그 범위)을 기준으로 Fragment임을 선언 |
| `th:replace`          | - 해당 속성은 fragment에서 지정된 태그 영역을 불러오는데 사용 <br> - 경로로 해당 파일을 찾아서 `::` 을 이용하여 Alias를 지정 |
| `layout:decorate`     | - 해당 속성은 구성한 레이아웃을 경로로 불러올때 사용 <br> - 업무 페이지에서 레이아웃 경로를 불러와야 Fragment를 사용 |
| `layout:insert`       | 레이아웃 파일의 특정 영역에 콘텐츠를 삽입하는 데 사용        |
| `layout:title`        | 페이지 제목을 정의하는 데 사용                               |

## 4. 사용법

### 1. 공통 layout 정의 및 구성

#### layout.html

```html
<!DOCTYPE html>
<html lang="ko"
                xmlns:th="http://www.thymeleaf.org"
                xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
>

<head th:replace="fragments/config :: ConfigFragment"></head>

<body>

  <th:block th:replace="fragments/header :: HeaderFragment"></th:block>

  <div data-aos="fade-down" data-aos-delay="0">
        <section layout:fragment="content"></section>
  </div>

  <th:block th:replace="/fragments/footer :: FooterFragment"></th:block>

  <th:block th:replace="/fragments/script :: ScriptFragment"></th:block>

  <script>
    AOS.init();
  </script>
</body>
</html>
```

### 2. Fragment 구성

#### 1. header.html

```html
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<!-- fragment변수 설정하고 block으로 감싸기-->
<th:block th:fragment="HeaderFragment">
    <!-- ======= Header content here ======= -->
    <!-- ======= Search Form ======= -->
    <!-- navbar -->
</header>
<!-- End Header -->
</th:block>
</html>
```

#### 2. footer.html

```html
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<!-- fragment변수 설정하고 block으로 감싸기-->
<th:block th:fragment="FooterFragment">
    <!-- ======= Footer content here ======= -->
    <footer id="footer" class="footer">
    </footer>
</th:block>
</html>
```

#### 3. config.html

```html
<!DOCTYPE html>
<!-- html tag에서 fragment선언 가능 -->
<html lang="en" th:fragment="ConfigFragment">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Store99st</title>
    <meta content="" name="description">
    <meta content="" name="keywords">
    <!-- Favicons -->
    <link href="/static/assets/img/favicon.png" rel="icon">
    <link href="/static/assets/img/apple-touch-icon.png" rel="apple-touch-icon">
    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <!-- Vendor CSS Files -->
    <link href="/static/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="/static/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
    <link href="/static/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="/static/assets/vendor/aos/aos.css" rel="stylesheet">
    <!-- Template Main CSS Files -->
    <link href="/static/assets/css/variables.css" rel="stylesheet">
    <link href="/static/assets/css/main.css" rel="stylesheet">
</head>
</html>
```

#### 3. script.html

```html
<body>
<th:block th:fragment="ScriptFragment">
    <a href="#" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

    <!-- Vendor JS Files -->
    <script src="/static/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="/static/assets/vendor/swiper/swiper-bundle.min.js"></script>
    <script src="/static/assets/vendor/glightbox/js/glightbox.min.js"></script>
    <script src="/static/assets/vendor/aos/aos.js"></script>

    <!-- Template Main JS File -->
    <script src="/static/assets/js/main.js"></script>
</th:block>
</body>
</html>
```

### 3. Controller

```java
@Controller
public class WelcomeController {
    
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null ){
            ip = request.getRemoteAddr();
        }
        model.addAttribute("ip",ip);
        return "cart/cart";
    }
}
```

### 4. 예시 : cart.html

```html
<!DOCTYPE html>
<html lang="en"
                xmlns:th="http://www.thymeleaf.org"
                xmlns:layout="http:www.ultraq.net.nz/thymeleaf/layout"
                layout:decorate="~{layouts/layout}"
>

<meta charset="UTF-8">
<div layout:fragment="content">
   <!-- cart content here -->
</div>
</html>
```

이 예제에서는 장바구니 페이지를 구현했습니다. 사용자는 체크박스를 통해 주문할 항목을 선택할 수 있고, 수량을 변경할 수 있습니다. 체크된 항목의 총 금액이 자동으로 계산되어 표시됩니다.

### 5. 실행 결과

![pj_적용후예시2](https://github.com/nhnacademy-be5-staff99/store99-front/assets/138862600/c489726f-b842-42f5-9688-aad7cae46ebf)

위 코드는 Thymeleaf Layout Dialect를 사용하여 웹 애플리케이션의 공통 레이아웃을 정의하고, 개별 페이지에서 해당 레이아웃을 가져와 사용하는 방법을 보여줍니다. 공통 레이아웃은 `layout.html`에 정의되어 있으며, 여기에는 `header`, `footer`, `script` 등의 공통 요소가 포함됩니다. 개별 페이지인 `cart.html`은 `layout:decorate`를 사용하여 `layout.html`을 가져오고, `layout:fragment="content"`를 통해 본문 내용을 삽입합니다.

전체적으로 Thymeleaf Layout Dialect를 사용하면 웹 애플리케이션의 코드 재사용성과 유지 보수성을 높일 수 있습니다. 공통 레이아웃을 한 곳에서 관리하면서 개별 페이지에서는 본문 내용만 작성하면 되기 때문입니다.
