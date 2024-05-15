package com.nhnacademy.store99.front.order.service.impl;

import com.nhnacademy.store99.front.book.adapter.BookOpenAdapter;
import com.nhnacademy.store99.front.book.dto.response.SimpleBookResponse;
import com.nhnacademy.store99.front.common.exception.FailedException;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.order.adapter.OrderOpenAdapter;
import com.nhnacademy.store99.front.order.dto.request.OrderBookRequest;
import com.nhnacademy.store99.front.order.dto.request.OrderInquiryByGuestRequest;
import com.nhnacademy.store99.front.order.dto.response.BookInOrderResponse;
import com.nhnacademy.store99.front.order.dto.response.OrderInquiryResponse;
import com.nhnacademy.store99.front.order.exception.CheckoutViewFailedException;
import com.nhnacademy.store99.front.order.service.OrderQueryService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author seunggyu-kim
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService {
    private final BookOpenAdapter bookOpenAdapter;
    private final OrderOpenAdapter orderOpenAdapter;

    @Override
    public List<BookInOrderResponse> getOrderBookList(final List<OrderBookRequest> orderBookRequestList) {
        List<SimpleBookResponse> simpleBooks = getSimpleBookResponsesByOrder(orderBookRequestList);

        Map<Long, Integer> bookIdToQuantityMap = orderBookRequestList.stream()
                .collect(Collectors.toMap(OrderBookRequest::getBookId, OrderBookRequest::getQuantity));

        return checkStockAndGenerateResponse(bookIdToQuantityMap, simpleBooks);
    }

    /**
     * 주문을 하는 도서 ID와 수량을 받아서 재고를 체크하고 응답을 생성한다.
     *
     * @param bookIdToQuantityMap 주문을 하는 도서 ID와 수량
     * @param simpleBooks         주문을 하는 도서에 대한 정보
     * @return 도서 정보 리스트
     * @throws CheckoutViewFailedException 재고가 부족할 때 발생한다.
     */
    private List<BookInOrderResponse> checkStockAndGenerateResponse(final Map<Long, Integer> bookIdToQuantityMap,
                                                                    final List<SimpleBookResponse> simpleBooks)
            throws CheckoutViewFailedException {
        List<BookInOrderResponse> orderBooks = new ArrayList<>();

        for (SimpleBookResponse simpleBook : simpleBooks) {
            if (simpleBook.getBookStock() < bookIdToQuantityMap.get(simpleBook.getId())) {
                throw new CheckoutViewFailedException(
                        String.format("%s 도서의 재고가 부족합니다. 재고: %d", simpleBook.getBookTitle(),
                                simpleBook.getBookStock()));
            }

            BookInOrderResponse orderBook = BookInOrderResponse.builder()
                    .bookId(simpleBook.getId())
                    .bookTitle(simpleBook.getBookTitle())
                    .bookPrice(simpleBook.getBookPrice())
                    .bookSalePrice(simpleBook.getBookSalePrice())
                    .bookThumbnailUrl(simpleBook.getBookThumbnailUrl())
                    .bookStock(simpleBook.getBookStock())
                    .quantity(bookIdToQuantityMap.get(simpleBook.getId()))
                    .build();

            orderBooks.add(orderBook);
        }

        return orderBooks;
    }

    /**
     * 주문을 하는 도서 ID와 수량을 받아서 도서 정보를 가져온다.
     *
     * @param orderBookRequestList
     * @return
     * @throws CheckoutViewFailedException 도서 정보를 가져오는데 실패했을 때 발생한다.
     */
    private List<SimpleBookResponse> getSimpleBookResponsesByOrder(final List<OrderBookRequest> orderBookRequestList)
            throws CheckoutViewFailedException {
        Set<Long> bookIds = orderBookRequestList.stream()
                .map(OrderBookRequest::getBookId)
                .collect(Collectors.toSet());
        CommonResponse<List<SimpleBookResponse>> getSimpleBooksResponse = bookOpenAdapter.getSimpleBooks(bookIds);

        if (!getSimpleBooksResponse.getHeader().isSuccessful()) {
            throw new CheckoutViewFailedException("bookOpenAdapter.getSimpleBooks failed");
        }
        List<SimpleBookResponse> simpleBooks = getSimpleBooksResponse.getResult();
        return simpleBooks;
    }

    @Override
    public OrderInquiryResponse getOrderByGuest(final OrderInquiryByGuestRequest orderInquiryRequest) {
        CommonResponse<OrderInquiryResponse> response = orderOpenAdapter.getOrderByGuest(orderInquiryRequest);
        if (!response.getHeader().isSuccessful()) {
            throw new FailedException("주문 내역을 가져오는데 실패하였습니다.");
        }
        return response.getResult();
    }
}
