(ns ticketcounter.client
  (:require [reagent.core :as r]
            [cljs-time.core :as t]
            [cljs-time.format :as f]))


(def date_format (f/formatter "yyyy-MM-dd"))


(def table '(["1984" "2017-10-14" :DRAMA]
             ["39 STEPS, THE " "2017-11-10" :COMEDY]
             ["A MIDSUMMER NIGHT’S DREAM - IN NEW ORLEANS" "2018-04-28" :DRAMA]
             ["ANNIE JR" "2018-03-11" :MUSICAL]
             ["AS IS" "2017-11-12" :DRAMA]
             ["AS YOU LIKE IT " "2018-03-16" :DRAMA]
             ["AUDIENCE, THE" "2018-05-05" :DRAMA]
             ["BAKKHAI" "2017-10-04" :DRAMA]
             ["BARBARIANS" "2018-06-01" :DRAMA]
             ["BEAUTIFUL - THE CAROLE KING MUSICAL " "2017-09-06" :MUSICAL]
             ["BEAUX' STRATAGEM, THE" "2017-12-03" :COMEDY]
             ["BEND IT LIKE BECKHAM THE MUSICAL" "2017-12-12" :MUSICAL]
             ["BILLY ELLIOT - THE MUSICAL" "2018-04-16" :MUSICAL]
             ["BOOK OF MORMON , THE" "2018-02-28" :MUSICAL]
             ["BRAINSTORM" "2018-03-02" :DRAMA]
             ["BREAKFAST AT TIFFANY'S" "2018-07-02" :DRAMA]
             ["CARETAKER, THE" "2018-06-28" :DRAMA]
             ["CATS" "2017-10-05" :MUSICAL]
             ["CHARLIE AND THE CHOCOLATE FACTORY - THE MUSICAL" "2018-04-18" :MUSICAL]
             ["COMEDY OF ERRORS" "2018-03-01" :COMEDY]
             ["COMMITMENTS, THE" "2017-09-16" :MUSICAL]
             ["CONSENSUAL" "2018-03-12" :DRAMA]
             ["CONSTELLATIONS" "2018-02-04" :DRAMA]
             ["CREDITORS" "2017-12-02" :DRAMA]
             ["CURIOUS INCIDENT OF THE DOG IN THE NIGHT-TIME, THE" "2018-01-11" :DRAMA]
             ["CYMBELINE" "2018-03-07" :DRAMA]
             ["DAWN FRENCH: 30 MILLION MINUTES " "2018-06-13" :COMEDY]
             ["DEAR LUPIN" "2018-05-09" :COMEDY]
             ["DEATH OF A SALESMAN" "2018-04-20" :DRAMA]
             ["DR SEUSS'S THE LORAX" "2017-10-24" :DRAMA]
             ["ELEPHANT MAN, THE" "2018-06-14" :DRAMA]
             ["ELF THE MUSICAL" "2018-02-28" :MUSICAL]
             ["ENTERTAINER, THE" "2018-06-11" :DRAMA]
             ["EVENING AT THE TALK HOUSE" "2017-09-16" :DRAMA]
             ["EVERYMAN" "2018-06-18" :DRAMA]
             ["FARINELLI AND THE KING" "2018-07-02" :DRAMA]
             ["FATHER, THE" "2017-09-08" :DRAMA]
             ["FUTURE CONDITIONAL " "2018-04-03" :DRAMA]
             ["GREEN DAY'S AMERICAN IDIOT" "2017-11-01" :MUSICAL]
             ["GROUNDHOG DAY" "2017-12-05" :MUSICAL]
             ["GUYS AND DOLLS" "2018-07-05" :MUSICAL]
             ["GYPSY" "2017-09-03" :MUSICAL]
             ["HAIRY APE, THE" "2018-05-18" :DRAMA]
             ["HAMLET" "2018-04-13" :DRAMA]
             ["HARLEQUINADE / ALL ON HER OWN (Double Bill)" "2018-05-10" :COMEDY]
             ["HARRY POTTER AND THE CURSED CHILD" "2018-04-28" :DRAMA]
             ["HAY FEVER" "2018-08-07" :COMEDY]
             ["HERE WE GO" "2018-06-10" :DRAMA]
             ["HERESY OF LOVE, THE" "2018-02-20" :DRAMA]
             ["HETTY FEATHER" "2018-02-06" :DRAMA]
             ["HIGH SOCIETY" "2018-05-07" :MUSICAL]
             ["HUSBANDS AND SONS" "2018-02-13" :DRAMA]
             ["I WANT MY HAT BACK" "2018-05-18" :DRAMA]
             ["IMPORTANCE OF BEING EARNEST, THE" "2018-01-10" :COMEDY]
             ["JANE EYRE" "2018-04-25" :DRAMA]
             ["JERSEY BOYS " "2017-10-24" :MUSICAL]
             ["KINKY BOOTS " "2018-08-23" :MUSICAL]
             ["LA MUSICA" "2018-01-01" :DRAMA]
             ["LES LIAISONS DANGEREUSES" "2018-02-22" :DRAMA]
             ["LES MISERABLES" "2018-02-13" :MUSICAL]
             ["LET IT BE" "2017-11-21" :MUSICAL]
             ["LIGHTS! CAMERA! IMPROVISE!" "2018-04-28" :COMEDY]
             ["LION KING, THE " "2018-01-09" :MUSICAL]
             ["LORD OF THE DANCE  - DANGEROUS GAMES" "2017-10-20" :MUSICAL]
             ["LORD OF THE FLIES, THE" "2017-09-04" :DRAMA]
             ["MA RAINEY'S BLACK BOTTOM" "2018-05-10" :DRAMA]
             ["MACBETH" "2018-05-17" :DRAMA]
             ["MACBETH (in Cantonese)" "2018-08-07" :DRAMA]
             ["MAMMA MIA!" "2018-06-24" :MUSICAL]
             ["MASTER BUILDER, THE" "2018-08-03" :DRAMA]
             ["MATILDA THE MUSICAL" "2018-03-27" :MUSICAL]
             ["MEASURE FOR MEASURE" "2018-08-08" :DRAMA]
             ["MEDEA" "2017-10-01" :DRAMA]
             ["MEMPHIS THE MUSICAL" "2018-05-10" :MUSICAL]
             ["MENTALISTS, THE" "2018-01-11" :COMEDY]
             ["MERCHANT OF VENICE, THE" "2018-05-03" :DRAMA]
             ["MISS SAIGON" "2017-12-20" :MUSICAL]
             ["MOTHERF**KER WITH THE HAT, THE" "2018-07-12" :DRAMA]
             ["MOTOWN THE MUSICAL" "2018-05-02" :MUSICAL]
             ["MOUSETRAP, THE " "2018-08-12" :DRAMA]
             ["MUCH ADO ABOUT NOTHING" "2018-07-18" :COMEDY]
             ["MY CHILDREN! MY AFRICA! " "2017-10-17" :DRAMA]
             ["NELL GWYNN" "2018-02-17" :DRAMA]
             ["NUMBER, A" "2018-01-23" :DRAMA]
             ["OMEROS" "2018-04-25" :DRAMA]
             ["ORESTEIA" "2018-06-21" :DRAMA]
             ["OUR COUNTRY'S GOOD" "2018-03-24" :DRAMA]
             ["PAINKILLER, THE" "2018-05-21" :COMEDY]
             ["PEOPLE, PLACES AND THINGS" "2018-03-27" :DRAMA]
             ["PERICLES" "2017-12-13" :DRAMA]
             ["PHANTOM OF THE OPERA, THE" "2018-07-25" :MUSICAL]
             ["PHOTOGRAPH 51" "2018-05-21" :DRAMA]
             ["PLAY THAT GOES WRONG, THE" "2017-12-12" :COMEDY]
             ["POMONA" "2018-06-21" :DRAMA]
             ["RED LION, THE" "2017-11-05" :DRAMA]
             ["RICHARD II" "2017-09-10" :DRAMA]
             ["RICHARD III (in Mandarin)" "2018-08-04" :DRAMA]
             ["ROMEO AND JULIET" "2018-03-04" :DRAMA]
             ["SEVEN BRIDES FOR SEVEN BROTHERS" "2018-08-02" :MUSICAL]
             ["SINATRA AT THE LONDON PALLADIUM" "2017-09-09" :MUSICAL]
             ["SONG FROM FAR AWAY" "2018-06-03" :DRAMA]
             ["SPLENDOUR" "2018-05-01" :DRAMA]
             ["STOMP" "2017-12-11" :MUSICAL]
             ["SUNNY AFTERNOON" "2018-06-24" :MUSICAL]
             ["TEDDY FERRARA" "2017-09-20" :DRAMA]
             ["TEMPEST, THE" "2018-01-28" :DRAMA]
             ["TEMPLE " "2018-05-07" :DRAMA]
             ["THE ODYSSEY: MISSING PRESUMED DEAD" "2017-11-04" :DRAMA]
             ["THOMAS TALLIS" "2017-11-07" :DRAMA]
             ["THREE DAYS IN THE COUNTRY" "2017-09-02" :DRAMA]
             ["THREE LITTLE PIGS, THE " "2017-11-08" :MUSICAL]
             ["THRILLER  LIVE" "2019-01-10" :MUSICAL]
             ["TO KILL A MOCKINGBIRD" "2017-10-17" :DRAMA]
             ["TRIAL, THE" "2018-02-23" :DRAMA]
             ["WAR HORSE" "2018-03-03" :DRAMA]
             ["WASTE" "2018-05-17" :DRAMA]
             ["WICKED " "2018-06-05" :MUSICAL]
             ["WINTER'S TALE, THE" "2018-06-23" :DRAMA]
             ["WOMAN IN BLACK, THE" "2018-02-10" :DRAMA]
             ["WONDER.LAND" "2018-01-21" :MUSICAL]
             ["WUTHERING HEIGHTS" "2017-10-25" :DRAMA]))


(def price {:MUSICAL 70
            :COMEDY 50
            :DRAMA 40})


(def discount {:day 80
               :price {:MUSICAL (* 0.8 (:MUSICAL price))
                       :COMEDY (* 0.8 (:COMEDY price))
                       :DRAMA (* 0.8 (:DRAMA price))}})


(def seats {:big 200
            :small 100})


(def days {:big {:from 0
                 :to 59}
           :small {:from 60
                   :to 99}})


(def sale_start_day 25)


(def available_per_day {:big 10
                        :small 5})


(defn add_show_days [acc from to show]
  "Add show to every day in period. Recursion: move from -> to."
  (if (t/after? from to)
    acc
    (let [key (f/unparse date_format from)
          val (get acc key)]
      (add_show_days
        (if (nil? val)
          (assoc acc key (list show))
          (assoc acc key (conj val show)))
        (t/plus from (t/days 1))
        to
        show))))


(defn big_hall_before_sale [acc row]
  "Business logic: Tickets not available before start sales."
  (let [title (nth row 0)
        open (f/parse date_format (nth row 1))
        genre (nth row 2)]
    (add_show_days
      acc
      (t/plus open (t/days (-> days :big :from)))
      (t/plus open (t/days (dec sale_start_day)))
      {:title title
       :hall :big
       :genre genre
       :tickets_left (-> seats :big)
       :tickets_available 0
       :status false
       :price (get price genre)})))


(defn big_hall_after_sale [acc row]
  "Business logic: Tickets available after start sales in big hall until show moves to small hall."
  (let [title (nth row 0)
        open (f/parse date_format (nth row 1))
        genre (nth row 2)]
    (add_show_days
      acc
      (t/plus open (t/days sale_start_day))
      (t/plus open (t/days (-> days :big :to)))
      {:title title
       :hall :big
       :genre genre
       :tickets_left (-> seats :big)
       :tickets_available (-> available_per_day :big)
       :status true
       :price (get price genre)})))


(defn small_hall_before_discount [acc row]
  "Business logic: Tickets have not discount 80 days."
  (let [title (nth row 0)
        open (f/parse date_format (nth row 1))
        genre (nth row 2)]
    (add_show_days
      acc
      (t/plus open (t/days (-> days :small :from)))
      (t/plus open (t/days (dec (-> discount :day))))
      {:title title
       :hall :small
       :genre genre
       :tickets_left (-> seats :small)
       :tickets_available (-> available_per_day :small)
       :status true
       :price (get price genre)})))


(defn small_hall_after_discount [acc row]
  "Business logic: Tickets have discount after 80 days."
  (let [title (nth row 0)
        open (f/parse date_format (nth row 1))
        genre (nth row 2)]
    (add_show_days
      acc
      (t/plus open (t/days (-> discount :day)))
      (t/plus open (t/days (-> days :small :to)))
      {:title title
       :hall :small
       :genre genre
       :tickets_left (-> seats :small)
       :tickets_available (-> available_per_day :small)
       :status true
       :price (get price genre)})))


(defn update_show [show]
  "Every new day there fixed number of tickets for sale. Just for open sale shows."
  (if (:status show)
    (assoc show :tickets_available (min
                                     (:tickets_left show)
                                     (-> available_per_day (:hall show))))
    show))

(defn create_model [data]
  "Main data structure for US-2 is map O(1). Key is date value is collection of show. For US-1 it is not suitable."
   (reduce
     (fn [acc, row]
       (-> acc
           (big_hall_before_sale row)
           (big_hall_after_sale row)
           (small_hall_before_discount row)
           (small_hall_after_discount row)))
     {}
     data))


(defn update_model! [state]
  "Update model O(n)"
  (swap! state assoc :model (reduce
                              (fn [model [k v]] k
                                (assoc model k (map update_show v)))
                              {}
                              (:model @state))))


(defn split_by_groups [shows]
  "Split show by groups: Musical, Comedy, Drama."
  (reduce
    (fn [acc show]
      (let [key (:genre show)]
        (assoc acc key (conj (get acc key) show))))
    {:MUSICAL '()
     :COMEDY '()
     :DRAMA '()}
    shows))


(defonce state (r/atom {:invalid true
                        :respond {:MUSICAL '()
                                  :COMEDY '()
                                  :DRAMA '()}
                        :model (create_model table)}))


(defn submit! []
  "Request shows by the query date next split by groups and move to state"
  (swap! state assoc :respond (split_by_groups (get (:model @state) (:query @state)))))


(defn next_day! []
  "Auto update available tickets according change of days. (In case user change day)"
  (update_model! state)
  (when (false? (:invalid @state)) (submit!)))


(defn validate [d]
  "Disable requests for past"
  (t/after? (f/parse date_format d) (t/local-date)))


(defn input_date! [d]
  "Invalid date at input should disable submit button"
    (if (validate d)
      (swap! state assoc :query d :invalid false)
      (swap! state assoc :invalid true)))


(defn shows_table [caption shows]
  "Render tables"
  [:table {:key caption :className "pure-table"}
   [:caption caption]
   [:thead
    [:tr
     [:th "Title"]
     [:th "Tickets Left"]
     [:th "Tickets Available"]
     [:th "Status"]
     [:th "Price"]]]
   [:tbody
    (for [show shows]
      [:tr {:key (:title show)}
       [:td (:title show)]
       [:td (:tickets_left show)]
       [:td (:tickets_available show)]
       [:td (if (:status show) "open for sale" "sale not started")]
       [:td (:price show)]])]])


(defn content []
  [:div
   [:label {:for "date"} "Show date:"]
   [:input {:name "date" :type "date" :on-change (fn [e] (input_date! (.-target.value e)))}]
   [:button {:on-click submit! :disabled (:invalid @state) :className "pure-button"} "Submit"]
   [shows_table "Musical" (:MUSICAL (:respond @state))]
   [shows_table "Comedy" (:COMEDY (:respond @state))]
   [shows_table "Drama" (:DRAMA (:respond @state))]])


(js/setInterval (fn [] (next_day!)) (* 24 60 60 1000))


(r/render-component [content]
  (.querySelector js/document "#app"))
