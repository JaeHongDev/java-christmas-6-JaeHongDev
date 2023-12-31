12월 이벤트

```

<애피타이저>
양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)

<메인>
티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)

<디저트>
초코케이크(15,000), 아이스크림(5,000)

<음료>
제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)

```

- [x] 중복된 할인과 증정을 허용함

### 이벤트 계획

- 크리스마스 디데이 할인
    - 2023.12.1 ~ 2023.12.25
    - 1000원으로 시작해서 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 늘어남
    - 12월 1일 1000원
    - 12월 25일 3400원

- 평일 할인
    - 평일에는 디저트 메뉴를 메뉴 1개당 2023원 할인
    - <u>디저트 메뉴가 몇 개인지 세어야 함</u>
- 주말 할인
    - 금요일, 토요일이 해당
    - 메인 메뉴 1개당 2023원 할인
    - 메인 메뉴가 몇 개인지 세어야 함

- 특별 할인
    - 이벤트 달력에 별이 있으면 총주문 금액에서 1000원 할인
- 증정 이벤트
    - 할인 전 총주문 금액이 12만원 이상일 때, 샴페인 1개 증정
- 이벤트 기간: 크리스마스 디데이 할인을 제외한 다른 이벤트는 2023.12.1 ~ 2023.12.31동안 적용

총 네 개의 할인 조건이 있음

혜택 금액에 따른 이벤트 배지 부여

### 도메인 추출

- 이벤트
- 결제 금액
- 할인 금액
- 날짜
- 주문
- 음식
- 가격
- 주문 목록

### 주문

- 주문 일자와 주문 항목을 가집니다.

#### 기능

- [x] 할인을 적용할 수 있습니다.
- [x] 주문 일자에 맞춰서 이벤트 할인을 적용합니다.
- [x] 할인 전 총 주문 금액을 구할 수 있습니다.
- [x] 할인 전 금액에 따라 샴페인 증정 메뉴를 확인할 수 있습니다.
- [x] 주문 메뉴와 수량을 가져올 수 있습니다.
- [x] 이벤트를 적용시켜 혜택 금액을 가져올 수 있습니다.
- [x] 할인 후 예상 결제 금액을 계산할 수 있습니다.
- [x] 예상 결제 금액에 따라 배찌를 만들 수 있습니다.

### 금액

- 정수로 이루어져 있습니다.

#### 기능

- `+` 기능을 가집니다.

+ `-` 기능을 가집니다.

### 배지

- 금액에 따라 배지를 만들어냅니다.
- 5000원은 별
- 10_000원은 트리
- 20_000원은 산타
- 외는 없음

### 주문 목록

- 주문항목을 가집니다.

#### 에러 조건

- [x] 최소 하나 이상의 주문을 가집니다.
- [x] 음료만 주문할 수 없습니다.
- [x] 20개 까지만 주문할 수 있습니다.

### 주문 항목

- 음식과, 수량을 가집니다.

#### 기능

- [x] 주문 항목에 대한 가격을 계산할 수 있습니다.
    - 음식 가격 * 수량
- [x] 음식의 이름을 문자열로 반환합니다.

#### 에러 조건

- [x] 존재하지 않는 음식이름을 가질 수 없습니다.
- [x] 수량의 경우 0이하일 수 없습니다.

### 날짜

- 12월로 고정됩니다.

### 음식

- 음식의 이름을 가지고 있습니다.
- 가격을 가집니다.

### 메뉴

- 카테고리에 음식목록을 가지고 있습니다.

### 나만의 원칙 정하기

- invoke라는 표현은 언제 사용하는게 좋을까?

# 요구사항 재점검

## 주문

- 방문일자와 주문목록을 가진다.

### 기능목록

- [x] 주문을 생성할 수 있습니다.
- [x] 주문한 메뉴와 수량을 조회할 수 있습니다.
- [x] 세부할인 내역을 조회할 수 있습니다.
- [x] 할인 전 금액을 계산할 수 있습니다.
- [x] 할인을 적용하려면 할인 전 금액 10_000원 이상부터 적용 가능합니다.

## 주문 목록

- 주문 항목을 가집니다.

### 검증 목록

- [x] 최대 주문 항목의 개수는 20개입니다.
- [x] 음료만 주문할 수 없습니다.
- [x] 메뉴는 하나 이상 주문 해야 합니다.

### 주문 항목

- 메뉴와 수량을 가집니다.

### 검증 목록

- [x] 수량은 하나 이상 존재해야 합니다.
- [x] 존재하지 않는 메뉴를 주문할 수 없습니다.

## 할인 조건

### 크리스마스 할인 조건

- [x] 크리스마스 이전 24일은 할인이 적용된다.
- [x] 크리스마스 이후 26일은 할인이 적용되지 않습니다.

### 평일 할인 조건

- [x] 일요일~목요일은 할인이 적용됩니다.

### 주말 할인 조건

- [x] 금요일-토요일 할인이 적용됩니다.

### 특별 할인 조건

- [x] 달력에 별이 있는 (3,10,17,24,25,31)에 할인이 적용됩니다.

### 증정 조건

- [x] 모든 일자에 적용 가능합니다.

---

## 할인

### 크리스마스 할인

- [x] 1일에는 1000원 2일에는 1100원 .... 25일에는 2400원이 적용됩니다.

### 주말 할인

- [x] 주말할인은 메인메뉴 1개당 2023원 할인 됩니다.
- [x] 메인 메뉴가 없어도 할인은 적용

### 할인 내역

<br>
<br>
<br>
<br>
<br>
<br>
