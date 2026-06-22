;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname IStation) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; IStation is one of:
;; - TStop
;; - CommStation
;; interp. a train station stop at either a subway station or a commuter line stop

(define-struct TStop (name line price))
;; TStop is (make-TStop String String Float)
;; interp. a subway station with its name, line name, and price

(define-struct CommStation (name line express))
;; CommStation is (make-CommStation String String Boolean)
;; interp. a commuter stop with its name, line name, and its express status

(define harvard (make-TStop "Harvard" "red" 1.25))
(define kenmore (make-TStop "Kenmore" "green" 1.25))
(define rivermore (make-TStop "Riverside" "green" 2.50))

(define backbay (make-CommStation "Back Bay" "Framingham" true))
(define wnewton (make-CommStation "West Newton" "Framingham" false))
(define wellhills (make-CommStation "Wellesley Hills" "Worcester" false))