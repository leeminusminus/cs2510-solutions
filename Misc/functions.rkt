;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname functions) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; functions.rkt

(define functions (list even?
                        (lambda (n) (and (positive? n) (odd? n)))
                        (lambda (n) (and (>= n 5) (<= n 10)))))

;; (listof (Number -> Boolean)) (listof Number) -> Boolean
;; produce true if a given list of numbers meets a list of conditions; otherwise produce false
(check-expect (meets-conditions functions (list 6 5)) true)
(check-expect (meets-conditions functions (list 4 3)) false)

;(define (meets-conditions lofn lon) false) ;stub

(define (meets-conditions lofn lon)
  (andmap (lambda (fn) (ormap fn lon)) lofn))

;; (listof (Number -> Boolean)) (listof Number) -> Boolean
;; produce true if there is at least one number for each of the list of conditions; otherwise produce false
(check-expect (meets-conditions-unique functions (list 6 5)) false)
(check-expect (meets-conditions-unique functions (list 6 5 6)) true)

;(define (meets-conditions-unique lofn lon) false) ;stub

(define (meets-conditions-unique lofn lon)
  (andmap (lambda (fn) (ormap fn (rest lon))) lofn))

;; (listof (Number -> Boolean)) (listof Number) -> Boolean
;; produce true if there is exactly one number for each of the list of conditions; otherwise produce false
(check-expect (all-meet-conditions functions (list 6 5 6)) true)
(check-expect (all-meet-conditions functions (list 6 5 42 6)) false)

;(define (all-meet-conditions lofn lon) false) ;stub

(define (all-meet-conditions lofn lon)
  (and (= (length lofn) (length lon))
       (meets-conditions-unique lofn lon)))