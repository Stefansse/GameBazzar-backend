--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1 (Debian 16.1-1.pgdg120+1)
-- Dumped by pg_dump version 16.1 (Debian 16.1-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: _order_items; Type: TABLE; Schema: public; Owner: sfn
--

CREATE TABLE public._order_items (
    order_item_id bigint NOT NULL,
    order_item_price double precision NOT NULL,
    quantity integer NOT NULL,
    game_game_id bigint,
    order_order_id bigint
);


ALTER TABLE public._order_items OWNER TO sfn;

--
-- Name: _order_items_order_item_id_seq; Type: SEQUENCE; Schema: public; Owner: sfn
--

CREATE SEQUENCE public._order_items_order_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public._order_items_order_item_id_seq OWNER TO sfn;

--
-- Name: _order_items_order_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sfn
--

ALTER SEQUENCE public._order_items_order_item_id_seq OWNED BY public._order_items.order_item_id;


--
-- Name: _reviews; Type: TABLE; Schema: public; Owner: sfn
--

CREATE TABLE public._reviews (
    review_id bigint NOT NULL,
    comment text,
    rating integer NOT NULL,
    review_date date NOT NULL,
    game_game_id bigint,
    user_user_id bigint
);


ALTER TABLE public._reviews OWNER TO sfn;

--
-- Name: _reviews_review_id_seq; Type: SEQUENCE; Schema: public; Owner: sfn
--

CREATE SEQUENCE public._reviews_review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public._reviews_review_id_seq OWNER TO sfn;

--
-- Name: _reviews_review_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sfn
--

ALTER SEQUENCE public._reviews_review_id_seq OWNED BY public._reviews.review_id;


--
-- Name: _ticket; Type: TABLE; Schema: public; Owner: sfn
--

CREATE TABLE public._ticket (
    id bigint NOT NULL,
    description character varying(255),
    email character varying(255),
    name character varying(255),
    problem_category character varying(255),
    resolved_at timestamp(6) without time zone,
    response character varying(255),
    status character varying(255),
    submitted_at timestamp(6) without time zone,
    user_id bigint
);


ALTER TABLE public._ticket OWNER TO sfn;

--
-- Name: _ticket_id_seq; Type: SEQUENCE; Schema: public; Owner: sfn
--

CREATE SEQUENCE public._ticket_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public._ticket_id_seq OWNER TO sfn;

--
-- Name: _ticket_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sfn
--

ALTER SEQUENCE public._ticket_id_seq OWNED BY public._ticket.id;


--
-- Name: _users; Type: TABLE; Schema: public; Owner: sfn
--

CREATE TABLE public._users (
    user_id bigint NOT NULL,
    address character varying(255),
    date_joined date,
    date_of_birth date,
    email character varying(255) NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255) NOT NULL,
    role character varying(255)
);


ALTER TABLE public._users OWNER TO sfn;

--
-- Name: _users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: sfn
--

CREATE SEQUENCE public._users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public._users_user_id_seq OWNER TO sfn;

--
-- Name: _users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sfn
--

ALTER SEQUENCE public._users_user_id_seq OWNED BY public._users.user_id;


--
-- Name: _wishlistitems; Type: TABLE; Schema: public; Owner: sfn
--

CREATE TABLE public._wishlistitems (
    wish_list_item_id bigint NOT NULL,
    quantity integer,
    game_game_id bigint,
    wish_list_wishlist_id bigint
);


ALTER TABLE public._wishlistitems OWNER TO sfn;

--
-- Name: _wishlistitems_wish_list_item_id_seq; Type: SEQUENCE; Schema: public; Owner: sfn
--

CREATE SEQUENCE public._wishlistitems_wish_list_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public._wishlistitems_wish_list_item_id_seq OWNER TO sfn;

--
-- Name: _wishlistitems_wish_list_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sfn
--

ALTER SEQUENCE public._wishlistitems_wish_list_item_id_seq OWNED BY public._wishlistitems.wish_list_item_id;


--
-- Name: _wishlists; Type: TABLE; Schema: public; Owner: sfn
--

CREATE TABLE public._wishlists (
    wishlist_id bigint NOT NULL,
    creation_date date,
    user_id bigint
);


ALTER TABLE public._wishlists OWNER TO sfn;

--
-- Name: _wishlists_wishlist_id_seq; Type: SEQUENCE; Schema: public; Owner: sfn
--

CREATE SEQUENCE public._wishlists_wishlist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public._wishlists_wishlist_id_seq OWNER TO sfn;

--
-- Name: _wishlists_wishlist_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sfn
--

ALTER SEQUENCE public._wishlists_wishlist_id_seq OWNED BY public._wishlists.wishlist_id;


--
-- Name: cart_items; Type: TABLE; Schema: public; Owner: sfn
--

CREATE TABLE public.cart_items (
    cart_item_id bigint NOT NULL,
    quantity integer NOT NULL,
    cart_cart_id bigint,
    game_game_id bigint
);


ALTER TABLE public.cart_items OWNER TO sfn;

--
-- Name: cart_items_cart_item_id_seq; Type: SEQUENCE; Schema: public; Owner: sfn
--

CREATE SEQUENCE public.cart_items_cart_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cart_items_cart_item_id_seq OWNER TO sfn;

--
-- Name: cart_items_cart_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sfn
--

ALTER SEQUENCE public.cart_items_cart_item_id_seq OWNED BY public.cart_items.cart_item_id;


--
-- Name: carts; Type: TABLE; Schema: public; Owner: sfn
--

CREATE TABLE public.carts (
    cart_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.carts OWNER TO sfn;

--
-- Name: carts_cart_id_seq; Type: SEQUENCE; Schema: public; Owner: sfn
--

CREATE SEQUENCE public.carts_cart_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.carts_cart_id_seq OWNER TO sfn;

--
-- Name: carts_cart_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sfn
--

ALTER SEQUENCE public.carts_cart_id_seq OWNED BY public.carts.cart_id;


--
-- Name: discount; Type: TABLE; Schema: public; Owner: sfn
--

CREATE TABLE public.discount (
    id bigint NOT NULL,
    code character varying(255) NOT NULL,
    end_date date NOT NULL,
    percentage double precision NOT NULL,
    start_date date NOT NULL,
    game_game_id bigint
);


ALTER TABLE public.discount OWNER TO sfn;

--
-- Name: discount_id_seq; Type: SEQUENCE; Schema: public; Owner: sfn
--

CREATE SEQUENCE public.discount_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.discount_id_seq OWNER TO sfn;

--
-- Name: discount_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sfn
--

ALTER SEQUENCE public.discount_id_seq OWNED BY public.discount.id;


--
-- Name: game; Type: TABLE; Schema: public; Owner: sfn
--

CREATE TABLE public.game (
    game_id bigint NOT NULL,
    description text,
    genre character varying(255),
    price double precision NOT NULL,
    publisher character varying(255),
    rating double precision,
    release_date date NOT NULL,
    title character varying(255) NOT NULL,
    discount_id bigint,
    image_url character varying(255),
    trailer_url character varying(255)
);


ALTER TABLE public.game OWNER TO sfn;

--
-- Name: game_game_id_seq; Type: SEQUENCE; Schema: public; Owner: sfn
--

CREATE SEQUENCE public.game_game_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.game_game_id_seq OWNER TO sfn;

--
-- Name: game_game_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sfn
--

ALTER SEQUENCE public.game_game_id_seq OWNED BY public.game.game_id;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: sfn
--

CREATE TABLE public.orders (
    order_id bigint NOT NULL,
    currency character varying(255),
    order_date date NOT NULL,
    order_size integer NOT NULL,
    status character varying(255) NOT NULL,
    amount double precision NOT NULL,
    user_user_id bigint
);


ALTER TABLE public.orders OWNER TO sfn;

--
-- Name: orders_order_id_seq; Type: SEQUENCE; Schema: public; Owner: sfn
--

CREATE SEQUENCE public.orders_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.orders_order_id_seq OWNER TO sfn;

--
-- Name: orders_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sfn
--

ALTER SEQUENCE public.orders_order_id_seq OWNED BY public.orders.order_id;


--
-- Name: _order_items order_item_id; Type: DEFAULT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._order_items ALTER COLUMN order_item_id SET DEFAULT nextval('public._order_items_order_item_id_seq'::regclass);


--
-- Name: _reviews review_id; Type: DEFAULT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._reviews ALTER COLUMN review_id SET DEFAULT nextval('public._reviews_review_id_seq'::regclass);


--
-- Name: _ticket id; Type: DEFAULT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._ticket ALTER COLUMN id SET DEFAULT nextval('public._ticket_id_seq'::regclass);


--
-- Name: _users user_id; Type: DEFAULT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._users ALTER COLUMN user_id SET DEFAULT nextval('public._users_user_id_seq'::regclass);


--
-- Name: _wishlistitems wish_list_item_id; Type: DEFAULT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._wishlistitems ALTER COLUMN wish_list_item_id SET DEFAULT nextval('public._wishlistitems_wish_list_item_id_seq'::regclass);


--
-- Name: _wishlists wishlist_id; Type: DEFAULT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._wishlists ALTER COLUMN wishlist_id SET DEFAULT nextval('public._wishlists_wishlist_id_seq'::regclass);


--
-- Name: cart_items cart_item_id; Type: DEFAULT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.cart_items ALTER COLUMN cart_item_id SET DEFAULT nextval('public.cart_items_cart_item_id_seq'::regclass);


--
-- Name: carts cart_id; Type: DEFAULT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.carts ALTER COLUMN cart_id SET DEFAULT nextval('public.carts_cart_id_seq'::regclass);


--
-- Name: discount id; Type: DEFAULT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.discount ALTER COLUMN id SET DEFAULT nextval('public.discount_id_seq'::regclass);


--
-- Name: game game_id; Type: DEFAULT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.game ALTER COLUMN game_id SET DEFAULT nextval('public.game_game_id_seq'::regclass);


--
-- Name: orders order_id; Type: DEFAULT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.orders ALTER COLUMN order_id SET DEFAULT nextval('public.orders_order_id_seq'::regclass);


--
-- Data for Name: _order_items; Type: TABLE DATA; Schema: public; Owner: sfn
--

COPY public._order_items (order_item_id, order_item_price, quantity, game_game_id, order_order_id) FROM stdin;
55	48.992999999999995	1	8	48
56	4.995	1	7	48
57	48.992999999999995	1	8	49
\.


--
-- Data for Name: _reviews; Type: TABLE DATA; Schema: public; Owner: sfn
--

COPY public._reviews (review_id, comment, rating, review_date, game_game_id, user_user_id) FROM stdin;
15	Great Game	5	2025-02-09	8	2
16	This is such a good game my god	5	2025-02-09	8	2
17	amazing	5	2025-02-09	8	2
\.


--
-- Data for Name: _ticket; Type: TABLE DATA; Schema: public; Owner: sfn
--

COPY public._ticket (id, description, email, name, problem_category, resolved_at, response, status, submitted_at, user_id) FROM stdin;
\.


--
-- Data for Name: _users; Type: TABLE DATA; Schema: public; Owner: sfn
--

COPY public._users (user_id, address, date_joined, date_of_birth, email, first_name, last_name, password, role) FROM stdin;
2	\N	2025-02-07	\N	sfnn.stoshevski@gmail.com	Stefan	Stosevski	$2a$10$ITZd0UsuwxRy0LwCOFfYF.F.e5gsAAt2wjlMETyDOtw95Slj5uvVa	ROLE_USER
3	\N	2025-02-07	\N	admin@gmail.com	Admin	Admin	$2a$10$9QLUDAqXRPZvdEOiNrAIK.8O26Kkd83vQmcBZ2n/mmjr6.UYYb8si	ROLE_ADMIN
4	\N	2025-02-07	\N	marjan_stoshevski@yahoo.com	Marjan	Stoshevski	$2a$10$ckCSUtlDzuiZnxyHVGXeNu/thWscrBm3kGVqFkmTGuqvTGmEJrk26	ROLE_USER
5	\N	2025-02-07	\N	marcor3us55555@gmail.com	Darko	Simov	$2a$10$NWAv/Tc4Vz8PivVOgUElhufb4aTyGKcfuNNpIUEojvVwiArxMoN0O	ROLE_USER
6	\N	2025-02-09	\N	darko.simov@gmail.com	Darko	Simov	$2a$10$dqIaKYz0y6F7QZbCVeustuK6u51JnpBU8s23MG5eSprYfAZqxprjq	ROLE_USER
\.


--
-- Data for Name: _wishlistitems; Type: TABLE DATA; Schema: public; Owner: sfn
--

COPY public._wishlistitems (wish_list_item_id, quantity, game_game_id, wish_list_wishlist_id) FROM stdin;
16	1	8	4
19	0	8	5
20	1	8	2
\.


--
-- Data for Name: _wishlists; Type: TABLE DATA; Schema: public; Owner: sfn
--

COPY public._wishlists (wishlist_id, creation_date, user_id) FROM stdin;
2	2025-02-07	2
3	2025-02-07	3
4	2025-02-07	4
5	2025-02-07	5
6	2025-02-09	6
\.


--
-- Data for Name: cart_items; Type: TABLE DATA; Schema: public; Owner: sfn
--

COPY public.cart_items (cart_item_id, quantity, cart_cart_id, game_game_id) FROM stdin;
\.


--
-- Data for Name: carts; Type: TABLE DATA; Schema: public; Owner: sfn
--

COPY public.carts (cart_id, user_id) FROM stdin;
2	2
3	3
4	4
5	5
6	6
\.


--
-- Data for Name: discount; Type: TABLE DATA; Schema: public; Owner: sfn
--

COPY public.discount (id, code, end_date, percentage, start_date, game_game_id) FROM stdin;
15	left 4 dead	2024-10-31	50	2024-10-01	7
16	god of war	2024-12-31	30	2024-10-01	8
17	path of exile	2024-10-31	20	2024-10-01	14
18	fairy tail	2024-10-31	40	2024-10-01	15
19	ravenwatch	2024-10-31	60	2024-10-01	18
20	behemoth	2024-10-31	10	2024-10-01	34
21	alien	2024-10-31	70	2024-10-01	24
22	frostpunk	2024-10-31	20	2024-10-01	9
23	blade runner	2024-10-31	10	2024-10-01	28
24	borneo	2024-10-31	5	2024-10-01	30
\.


--
-- Data for Name: game; Type: TABLE DATA; Schema: public; Owner: sfn
--

COPY public.game (game_id, description, genre, price, publisher, rating, release_date, title, discount_id, image_url, trailer_url) FROM stdin;
4	Farming Simulator is a farming simulation video game series developed by GIANTS Software. The locations are based on American, European and Asian environments. Players are able to farm, breed livestock, grow crops, and sell assets created from farming. The games have sold over 25 million copies combined, as well as had 90 million mobile downloads.[3] The game was originally revised, expanded, and re-released every two years, (excluding their newest two releases) with better graphics, a larger array of vehicles, and more interesting tasks for the user to perform.	SIMULATION	49.99	Steam	6	2024-08-01	Farming Simulator 25	\N	https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/2300320/header.jpg?t=1731424435	https://www.youtube.com/embed/X868ycV8ZMQ
8	God of War Ragnar├╢k is an action-adventure game where players take the role of Kratos and his son as they embark on a quest to prevent the coming of Ragnar├╢k. Players explore the Nine Realms, battling enemies in close-up, hand-to-hand combat against human-like raiders and fantastical creatures.	ACTION	69.99	Sony	9.6	2024-08-01	God Of War Ragnarok	16	https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/2322010/capsule_616x353.jpg?t=1728067832	https://www.youtube.com/embed/hfJ4Km46A-0
7	In Left 4 Dead, the four survivors must fight off infected humans while trying to make their way to a safe house/rescue vehicle. Left 4 Dead is a first-person shooter in which the player takes control of one of four survivors; if human players are not available, the remaining survivors are filled by AI-controlled bots.	ACTION	9.99	Steam	1	2024-08-01	Left 4 Dead	15	https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/550/capsule_616x353.jpg?t=1731457037	https://www.youtube.com/embed/9XIle_kLHKU
2	Dota 2 is a Dota 2 is a 2013 multiplayer online battle arena (MOBA) video game by Valve. The game is a sequel to Defense of the Ancients (DotA), a community-created mod for Blizzard Entertainment's Warcraft III: Reign of Chaos. Dota 2 is played in matches between two teams of five players, with each team occupying and defending their own separate base on the map. Each of the ten players independently controls a character known as a hero that has unique abilities and differing styles of play. During a match, players collect experience points (XP) and items for their heroes to defeat the opposing team's heroes in player versus player (PvP) combat. A team wins by being the first to destroy the other team's Ancient, a large durable structure located in the center of each base.\n            Development of Dota 2 began in 2009 when IceFrog, lead designer of Defense of the Ancients, was hired by Valve to design a modernized remake in the Source game engine. It was released for Windows, OS X, and Linux via the digital distribution platform Steam in July 2013, following a Windows-only open beta phase that began two years prior. The game is fully free-to-play with no heroes or any other gameplay element needing to be bought or otherwise unlocked. To maintain it, Valve supports the game as a service, selling loot boxes and a battle pass subscription system called Dota Plus that offer non-gameplay altering virtual goods in return, such as hero cosmetics and audio replacement packs. The game was ported to the Source 2 engine in 2015, making it the first game to use it. Dota 2 has a large esports scene, with teams from around the world playing in various professional leagues and tournaments. Valve organizes the Dota Pro Circuit, which are a series of tournaments that award qualification points for earning direct invitations to The International, the game's premier tournament held annually. Internationals feature a crowdfunded prize money system that has seen amounts in upwards of US$40 million, making Dota 2 one of the most lucrative esports. Media coverage of most tournaments is done by a selection of on-site staff who provide commentary and analysis for the ongoing matches similar to traditional sporting events. In addition to playing live to audiences in arenas and stadiums, broadcasts of them are also streamed over the internet and sometimes simulcast on television, with several million in viewership numbers. online battle arena game (MOBA), a sequel to Dota, which stands for Defence of the Ancients, which itself arose from Warcraft III: Reign of Chaos. It is one of the very few popular games that is truly communal, having been conceived, tested and released on community fora. About the Game In the game you are divided into teams of five and battle against another similar team ΓÇô all these warring groups of ten are in distinct places on the map. Each of the ten players c...	MMORPG	0	Steam	6.7	2024-08-20	Dota 2	\N	https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/570/capsule_616x353.jpg?t=1731033201	https://www.youtube.com/embed/-cSFPIwMEq4
6	Sons Of The Forest on Steam. Sent to find a missing billionaire on a remote island, you find yourself in a cannibal-infested hellscape. Craft, build, and struggle to survive, alone or with friends, in this terrifying new open-world survival horror simulator.	ADVENTURE	29.99	Steam	5	2024-08-01	Sons Of The Forest	\N	https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/1326470/capsule_616x353.jpg?t=1708624856	https://www.youtube.com/embed/8sghWJKPWno
5	Rocket League is a video game the combines arcade-style soccer and driving games. You play by controlling rocket-powered vehicles, which you can use to score goals with a giant soccer ball. Gameplay is energetic and chaotic as the cars can flip and fly in all directions.	SPORTS	0	Epic	8.3	2024-08-01	Rocket League	\N	https://www.rocketleague.com/images/keyart/rl_evergreen.jpg	https://www.youtube.com/embed/KE07hpCDllU
1	Valorant is an online multiplayer computer game, produced by Riot Games. It is a first-person shooter game, consisting of two teams of five, where one team attacks and the other defends. Players control characters known as ΓÇÿagentsΓÇÖ, who all have different abilities to use during gameplay. The gameΓÇÖs matchmaking system automatically groups players of similar skill together.The game is played in a series of rounds until a team wins a total of 13 rounds in a game. For the attacking team to win a round, they must eliminate all the defenders with their weapons and abilities, or plant a bomb known as a ΓÇÿspikeΓÇÖ in a designated site. For the defending team to win a round, they must eliminate all the attackers with their weapons and abilities or defuse the ΓÇÿspikeΓÇÖ in time.Valorant is complex, taking elements of other games, such as Counter Strike, Overwatch and League of Legends. It attracts gamers of all levels, including elite players who compete in global eSports tournaments for real prize money. \nValorant players can use voice chat and text chat to communicate with other players in the game. Premium content can also be earned or purchased in the game.	ACTION	0	Riot	4.7	2024-08-01	Valorant	\N	https://cdn1.epicgames.com/offer/cbd5b3d310a54b12bf3fe8c41994174f/EGS_VALORANT_RiotGames_S1_2560x1440-6608fb6c4c56c07b9a7caa34c6e6ee37	https://www.youtube.com/embed/e_E9W2vsRbQ
3	League of Legends (LoL), commonly referred to as League, is a 2009 multiplayer online battle arena video game developed and published by Riot Games. Inspired by Defense of the Ancients, a custom map for Warcraft III, Riot's founders sought to develop a stand-alone game in the same genre. Since its release in October 2009, League has been free-to-play and is monetized through purchasable character customization. The game is available for Microsoft Windows and macOS.\n\nIn the game, two teams of five players battle in player-versus-player combat, each team occupying and defending their half of the map. Each of the ten players controls a character, known as a "champion", with unique abilities and differing styles of play. During a match, champions become more powerful by collecting experience points, earning gold, and purchasing items to defeat the opposing team. In League's main mode, Summoner's Rift, a team wins by pushing through to the enemy base and destroying their "Nexus", a large structure located within.\n\nLeague of Legends has received generally positive reviews; critics have highlighted its accessibility, character designs, and production value. The game's long lifespan has resulted in a critical reappraisal, with reviews trending positively; it is widely considered one of the greatest video games ever made. However, negative and abusive in-game player behavior, criticized since the game's early days, persists despite Riot's attempts to fix the problem. In 2019, League regularly peaked at eight million concurrent players, and its popularity has led to tie-ins such as music, comic books, short stories, and the animated series Arcane. Its success has spawned several spin-off video games, including a mobile version, a digital collectible card game, and a turn-based role-playing game, among others. A massively multiplayer online role-playing game based on the property is in development.	MMORPG	0	Riot	8.3	2024-08-01	League Of Legends	\N	https://m.media-amazon.com/images/M/MV5BMmEzYzQ2ZGQtNmU2NC00ZDFkLTg4NWItNDQwZGM0OTlkMWYyXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg	https://www.youtube.com/embed/RprbAMOPsH0
14	Path of Exile 2 is a next generation free-to-play Action RPG from Grinding Gear Games, featuring co-op for up to six players. Set years after the original Path of Exile, you will return to the dark world of Wraeclast and seek to end the corruption that is spreading.	ROLE_PLAYING	39.99	Steam	10	2024-11-01	Path of Exile 2 	17	https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/2694490/capsule_616x353.jpg?t=1731616557	https://www.youtube.com/embed/9OosC1E8TVM
16	Build an industrial empire from scratch in Industry Giant 4.0. Produce goods, build up your industries, construct transportation networks, plan production, manage finances, and navigate global events. Become the next business mogul and write history!	STRATEGY	19.99	Epic	5	2024-12-01	Industry Giant 4.0	\N	https://cdn1.epicgames.com/spt-assets/b7d55c5d63d54d3985b57c35af336be9/industry-giant-40-1s58i.jpg	https://www.youtube.com/embed/X4rTSZI7BNo
12	Divine Dynamo Flamefrit is a 2D action game inspired by classic games and anime. Play as the hero Yuto in top-down slashing adventure stages, and pilot the magical robot Flamefrit for first-person boss battles! Master both styles of gameplay to restore peace to the parallel world of Hologard!	ADVENTURE	5.99	Steam	7	2024-12-01	Divine Dynamo Flamefrit	\N	https://i.ytimg.com/vi/OCnNidHOA0E/maxresdefault.jpg	https://www.youtube.com/embed/OCnNidHOA0E
15	This sequel to the game "FAIRY TAIL," released in July 2020, depicts the ΓÇ£Alvarez Empire Arc,ΓÇ¥ the climax of the original manga. - Relive the culmination of the original series through gameplay! This RPG depicts the dramatic developments of the ΓÇ£Alvarez Empire Arc,ΓÇ¥ the climax of the original story.	ROLE_PLAYING	69.99	Steam	8	2024-11-01	FAIRY TAIL 2	18	https://fairytailgame.com/2/assets/img/uk/og.jpg	https://www.youtube.com/embed/VM4JuKGW2Nk
13	The human world, threatened by mechteria... a machine world, filled with mysteries... and the unseen dimensions that lie beyond both. Using the Warp Device that was left behind, Leo must now embark on an interdimensional journey to reclaim his lost memories and save the world from the mechteria infection.	ADVENTURE	49.99	Steam	9	2024-11-01	FANTASIAN Neo Dimension\n	\N	https://clan.fastly.steamstatic.com/images/45144893/66930c44cd05c5e14d499b1f47957af91b6ea150_400x225.jpg	https://www.youtube.com/embed/a3Gx7Tp24iI
19	\n\nVED is a story-driven RPG with a unique turn-based combat system and rogue-lite elements. Embark on a magical adventure set in a vivid world in stunning, hand-painted graphics. Explore mysterious islands, defeat monsters, and master the magic of the Veds! The free version "Purification" serves as a prologue to the main game so you can sample its combat system, narrative style and teleportation between the magical and human worlds, but not spoil the full story for yourself! Its events are set 30 years prior to the narrative of VED, following a different character, a Vedic warrior by the name of Dook, who fell in love with a magical being. But as the Vedic order is sworn to keep the magical and the mundane worlds separate, eliminating any semblance of magic from the human world, theirs is certainly a complicated love story; and itΓÇÖs up to you to decide how it will endΓÇª 	ADVENTURE	19.99	Steam	8	2024-11-01	VED	\N	https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/1182420/capsule_616x353.jpg?t=1731608008	https://www.youtube.com/embed/ky9L6AFB6Cw
10	S.T.A.L.K.E.R. 2: Heart of Chornobyl is a sequel to the award-winning game franchise developed by GSC Game World. Experience one-of-a-kind gameplay, featuring the elements of a first-person shooter, immersive sim and horror. The Chornobyl Exclusion Zone is a unique, dangerous and ever-changing environment.	FPS	60	Steam	8	2024-11-01	S.T.A.L.K.E.R. 2: Heart of Chornobyl - Europe	\N	https://gaming-cdn.com/images/products/5376/orig/s-t-a-l-k-e-r-2-heart-of-chornobyl-pc-game-steam-europe-cover.jpg?v=1730967409	https://www.youtube.com/embed/pzyL9lFLhbU
11	The future starts here. After two decades of innovation, this is year one of a new legacy.\n\nHereΓÇÖs a taste of whatΓÇÖs to comeΓÇª\n\nEvery career begins with a choice. When it comes to selecting a club, the newly-licensed Premier League raises your immersion to new levels. Compete in the worldΓÇÖs biggest league or break new ground as WomenΓÇÖs Football makes its series debut, seamlessly joining the menΓÇÖs game. One world, one ecosystem.\n\nWith this influx of star power, FM25 has more playable leagues and nations than ever ΓÇô football gamingΓÇÖs biggest player database has been propelled to new heights.\n\nFlex your tactical prowess and uncover a playing style that unlocks the joy of watching your team play football how you want it to be played. Whatever Matchday throws at you, feel the passion of every passage of play with our switch to the Unity engine - FMΓÇÖs biggest technical and visual advancement for a generation. These are the new foundations for our next chapter.\n\nA slicker, smarter UI delivers key info when you need it, empowering you to delve deeper to gain the edge on your rivals. New, volumetric player animations from the real world of football and enhanced graphical fidelity elevate the Matchday experience. Get ready to leave your reality behind and live your game.\n\nStep into the dugout and unlock passion that no other game can rival and discover even more reasons to love the beautiful game.	SPORTS	59.99	Steam	6	2024-11-01	Football Manager 25	\N	https://cdn.footballmanager.com/site/2024-09/Opengraph%202_1.jpg	https://www.youtube.com/embed/ob06By9TFOA
17	Toads of the Bayou on Steam. Lead a group of toads as they fight against Baron Samedi's evil forces in a challenging mix of turn-based tactics and roguelike deckbuilding. Synergise card effects, gain the aid of powerful allies and engage in strategic combat to thrive in the cursed bayou.	STRATEGY	29.99	Steam	6	2024-11-01	Toads of the Bayou\n	\N	https://assets-prd.ignimgs.com/2023/06/02/toadsbayou-thumb-1685739424742.jpg	https://www.youtube.com/embed/2BZn1NiM1HM
18	This is a roguelite on a large scale, in which you play as one of an ever-larger number of characters, given three in-game days (20 minutes) to action-RPG battle your way around a procedurally generated sprawling level, fighting mobs, gathering loot, and upgrading your character's abilities, all focused on surviving	ROLE_PLAYING	24.99	Steam	8	2024-11-01	Ravenswatch	19	https://ravenswatch.com/wp-content/uploads/2024/09/Ravenswatch_Keyart_16x9_b-scaled.jpg	https://www.youtube.com/embed/XdF3S4g8ubI
34	In Skydance's BEHEMOTH, take on the role of Wren, a lone hunter banished to the cursed Forsaken Lands. Overcome dangerous traps, deadly marauders, and slay towering Behemoths using immersive, weighty VR combat and life-like physics. Revel in the decaying remnants of this dying kingdom.	ADVENTURE	49.99	Steam	7	2024-12-01	Skydance's Behemoth	20	https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSP51NVG6DrHq0hOz-UsjV6JrQ5gRJf4Kzqfg&s	https://www.youtube.com/embed/U8qwCSfusNo
9	Frostpunk 2 is a city-building survival video game developed and published by 11 Bit Studios. Set 30 years after the original game, Frostpunk 2 tasks players to take on the role of a leader in an alternate history early 20th century in order to build and manage a city during a catastrophic, worldwide volcanic winter that almost completely destroyed human civilization while making morally and politically controversial choices to ensure its survival. The game was released for macOS and Windows PC on September 20, 2024, with a planned release for PlayStation 5 and Xbox Series X and Series S later in 2025. Like its predecessor, Frostpunk 2 is a city-building survival video game. Set in New London in 1916, 30 years after the "Great Storm" of the original game, the game explores the consequences of the advent of the petroleum industry and players assume the role of the city's new leader, "the Steward", who replaces the now-deceased "Captain" (the player character from the first game). The city is struggling with overpopulation, food and coal shortages, among other issues. Unlike the original game, Frostpunk 2 allows players to build a much larger city. Players can now construct districts instead of individual buildings. Each district serves a particular function, such as providing food, energy, or shelter. Specific buildings can be placed in each district to unlock additional functions.Each district, however, also costs players resources, and players need to plan the design of their cities before expanding.	STRATEGY	23.89	Steam	7	2025-01-01	Frostpunk 2	22	https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/1601580/header.jpg?t=1728289067	https://www.youtube.com/embed/BuvJAcptUd0
21	Delta Force is a free-to-play first-person tactical shooter. As the latest installment of the classic series which has defined the genre for 25 years, Delta Force is back with an upgraded arsenal of weaponry, jaw-dropping environments, and a truly dynamic world.	FPS	0	Steam	7	2024-12-01	Delta Force	\N	https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/2507950/aa76abf07dfd9c61e167c8d188c2ea522f1992b6/capsule_616x353.jpg?t=1731566649	https://www.youtube.com/embed/X8Ck8hTeBvg
22	Go Home Annie on Steam. A twisted psychological thriller and an original story set in the SCP universe. As an employee of the SCP Foundation, test artificially created paranormal events, solve puzzles, interact with anomalies, and uncover the secrets of the Replication Division.	PUZZLE	19.99	Epic	7	2024-12-01	Go Home Annie	\N	https://images.gog-statics.com/ffaa5af29adf35bbb6b483c73ac03fa7aceb1caaf98f0c7f3f05b4dc5161315a_product_card_v2_mobile_slider_639.jpg	https://www.youtube.com/embed/Tg24vDp52Qk
23	We Are Legion is the first DLC for Terminator: Dark Fate - Defiance, a gritty real-time strategy game set in the intense universe of Terminator: Dark Fate.It plunges players back into the heart of the conflict as they take command of Legion, the relentless machine army fighting to dominate a shattered world. This DLC expands on the story, focusing on LegionΓÇÖs mission to eradicate human resistance and assert dominance over a world ravaged by war.A New Single-	FPS	59.99	Rockstar	6	2024-12-01	Terminator: Dark Fate - Defiance: We are Legion	\N	https://gaming-cdn.com/images/products/17991/orig/terminator-dark-fate-defiance-we-are-legion-pc-game-steam-cover.jpg?v=1730986474	https://www.youtube.com/embed/ZMh5FuvZOzg
25	Assume the role of Takeshi, a Japanese samurai who has to protect his village from the onslaught of an Oni attempting to conquer the land with his undead army. Fight against his army of tengu, undead and the horrifying Jorogumo, all inspired by Japanese mythology, in a unique, brutal stop-motion cinematic adventure.Defend the Forbidden Village Explore a meticulously crafted world alive with Japanese mythology and folklore. Wander through ruined villages	ROLE_PLAYING	0	Rockstar	6	2025-01-01	The Spirit of the Samurai	\N	https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTbBAZy4YyTnN6x5UlEYFhIUWjfaWlrTuk-Dw&s	https://www.youtube.com/embed/BocRzKoYF4k
26	Unleash your inner demon in BEAST, a brutal, turn-based tactical RPG thatΓÇÖs currently available in Early Access. Serving as False ProphetΓÇÖs debut title, BEAST aims to redefine the turn-based tactics genre by telling a gritty, grounded story with morally ambiguous characters. With gridless, turn-based tactics gameplay at the forefront, players are able to strategize to take down their foes on the battlefield in the most visceral ways possible. But you donΓÇÖt need to venture forth alone. You	ACTION	0	Epic	7	2025-01-01	Beast	\N	https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/1145630/header.jpg?t=1726944188	https://www.youtube.com/embed/eUL_1_zDb14
27	As a young journalist, youΓÇÖve been entrusted with a crucial mission: to cross the globe and reach the heart of a mysterious, faraway landΓÇª Your goal is to bring back the last egg of the mythical ΓÇ£Great White BirdsΓÇ¥. According to legend, they are bound up with the fate of the only country they call home: a land with strange, dangerous plants and wildlife that has been devastated by years of bloody dictatorship. Amerzone - The Explorer's Legacy will call on your curiosity and	ADVENTURE	23.89	Steam	7	2025-01-01	Amerzone - The Explorer's Legacy\n	\N	https://i.ytimg.com/vi/lQf1kBoskWw/maxresdefault.jpg	https://www.youtube.com/embed/lQf1kBoskWw
24	Alien: Rogue Incursion is an all-new single-player, action-horror VR game featuring an original story that fully immerses players within the terrors of the Alien universe. Designed by Alien fans for Alien fans, Survios brings their expertise to crafting this frightfully immersive Alien virtual reality game. IMMERSE YOURSELF IN THE ALIEN UNIVERSE Experience the horror and challenge of fighting Xenomorphs in a world inspired by the look and feel of the Alien film	ACTION	89.99	Steam	8	2024-12-01	Alien: Rogue Incursion	21	https://www.gematsu.com/wp-content/uploads/2024/08/Alien-Rogue-Incursion_2024_08-14-24_002.jpg	https://www.youtube.com/embed/KAO6QKmlAxE
29	An Epic Journey Through Time You are on a quest to uncover the truth about your origins. On your travels you meet people from many worlds and make new friends with whom you share your adventures. Eventually, you find yourself facing an inescapable destiny that will determine the fate of planet Regnas. Action-Packed Combat It doesn't matter if you're a beginner or an advanced player, the action-based combat system features simple controls that are	ROLE_PLAYING	0	Epic	7	2025-01-01	Blue Protocol	\N	https://www.bandainamcostudios.com/en/wp-content/uploads/sites/2/2023/06/BlueProtocol_%E3%83%87%E3%83%95%E3%82%A9%E3%83%AB%E3%83%88%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7_re.png	https://www.youtube.com/embed/JWZ83y-KfoY
31	ALARM! You have been selected for a mission which will shape the fate of the entire world. Witness the very beginning of the legendary elite WWII force in Commandos: Origins. The long-awaited sequel to the Commandos series brings you right back to the foundation of the real-time tactics genre. And to the days where Jack OΓÇÖHara, the Green Beret, and his five companions met to form the infamous unit sent to complete missions which no others would	STRATEGY	0	Steam	7	2025-01-01	Commandos: Origins	\N	https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/1479730/capsule_616x353.jpg?t=1730282364	https://www.youtube.com/embed/UOpbCYnT7NM
28	"Lots of people lost things in the Blackout. I was lucky, I just lost a job. But now they want me back." From Annapurna Interactive, Blade Runner 2033: Labyrinth brings the revered franchise back to games for the first time in 25 years. Set between the original film and 2049, Blade Runner 2033 takes place after the events of the Blackout, the devastating terrorist attack that brought an end to Tyrell Corp and Replicant production. More details about the game will be	ACTION	69.99	Steam	7	2025-01-01	Blade Runner 2033: Labyrinth\n	23	https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/2012260/capsule_616x353.jpg?t=1709160668	https://www.youtube.com/embed/b_PXuEPxN50
30	Chicago, 1999 A newspaper editor waits for a reporter to deliver a documentary about an expedition in Borneo to find the last cannibals. The man does not show up, but an envelope is delivered to his office. It contains a bloodstained videotape and a concise message: ΓÇ£It is not the video you were expecting, but I think you will be satisfied anyway. Try to enjoy your last night.ΓÇ¥ Who is threatening you? What happened to the reporter? And why do the members of the	ADVENTURE	49.89	Steam	7	2025-01-01	Borneo: A Jungle Nightmare	24	https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/1286420/header.jpg?t=1714740047	https://www.youtube.com/embed/oSwXuJKKyec
20	In the realm of Symphonia, music acts as a source of life and energy. But since the founders and their orchestra vanished the inhabitants are divided, and the world slowly falls into silence. What if there was a way to gather a new orchestra? That's the question Philemon, the mysterious musician in Symphonia, will have to answer. Embody a mysterious violinist, brought back to life in a declining world. Your violin and bow are both your instrument and a means of transport. Catapult yourself through the world, and complete levels by solving exciting platforming challenges while re-activating the machinery that brings life and energy back to this world.Discover a musical world, based on the symphonic orchestra, and filled with colourful characters. Each area in Symphonia has its own distinct feel and characters, and each room is hand-crafted. Meet prodigious musicians and try to convince them to join the orchestra.A unique soundtrack, based on the romantic musical period, crafted by composer Olivier Esman. Enjoy the performance of the Scoring Orchestra Paris, recorded specifically for Symphonia and written by composer Olivier Esman and his partners Alexandre Bucas-Fran├ºais and Lou Corroyer. Play engrossing concerts, each centred around one of the families of instruments ΓÇô Strings, Brass and Woodwinds. Explore, navigate, play the violin, gather musicians. Awaken Symphonia.\n\n	ROLE_PLAYING	19.89	Steam	9	2024-12-01	Symphonia	\N	https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/1865960/capsule_616x353.jpg?t=1731415573	https://www.youtube.com/embed/vOtQLi7vhJE
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: sfn
--

COPY public.orders (order_id, currency, order_date, order_size, status, amount, user_user_id) FROM stdin;
48	USD	2025-02-10	2	COMPLETED	53.98799999999999	2
49	USD	2025-02-11	1	COMPLETED	48.992999999999995	2
\.


--
-- Name: _order_items_order_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sfn
--

SELECT pg_catalog.setval('public._order_items_order_item_id_seq', 57, true);


--
-- Name: _reviews_review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sfn
--

SELECT pg_catalog.setval('public._reviews_review_id_seq', 17, true);


--
-- Name: _ticket_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sfn
--

SELECT pg_catalog.setval('public._ticket_id_seq', 8, true);


--
-- Name: _users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sfn
--

SELECT pg_catalog.setval('public._users_user_id_seq', 6, true);


--
-- Name: _wishlistitems_wish_list_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sfn
--

SELECT pg_catalog.setval('public._wishlistitems_wish_list_item_id_seq', 21, true);


--
-- Name: _wishlists_wishlist_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sfn
--

SELECT pg_catalog.setval('public._wishlists_wishlist_id_seq', 6, true);


--
-- Name: cart_items_cart_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sfn
--

SELECT pg_catalog.setval('public.cart_items_cart_item_id_seq', 88, true);


--
-- Name: carts_cart_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sfn
--

SELECT pg_catalog.setval('public.carts_cart_id_seq', 6, true);


--
-- Name: discount_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sfn
--

SELECT pg_catalog.setval('public.discount_id_seq', 15, true);


--
-- Name: game_game_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sfn
--

SELECT pg_catalog.setval('public.game_game_id_seq', 4, true);


--
-- Name: orders_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sfn
--

SELECT pg_catalog.setval('public.orders_order_id_seq', 49, true);


--
-- Name: _order_items _order_items_pkey; Type: CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._order_items
    ADD CONSTRAINT _order_items_pkey PRIMARY KEY (order_item_id);


--
-- Name: _reviews _reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._reviews
    ADD CONSTRAINT _reviews_pkey PRIMARY KEY (review_id);


--
-- Name: _ticket _ticket_pkey; Type: CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._ticket
    ADD CONSTRAINT _ticket_pkey PRIMARY KEY (id);


--
-- Name: _users _users_pkey; Type: CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._users
    ADD CONSTRAINT _users_pkey PRIMARY KEY (user_id);


--
-- Name: _wishlistitems _wishlistitems_pkey; Type: CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._wishlistitems
    ADD CONSTRAINT _wishlistitems_pkey PRIMARY KEY (wish_list_item_id);


--
-- Name: _wishlists _wishlists_pkey; Type: CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._wishlists
    ADD CONSTRAINT _wishlists_pkey PRIMARY KEY (wishlist_id);


--
-- Name: cart_items cart_items_pkey; Type: CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.cart_items
    ADD CONSTRAINT cart_items_pkey PRIMARY KEY (cart_item_id);


--
-- Name: carts carts_pkey; Type: CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT carts_pkey PRIMARY KEY (cart_id);


--
-- Name: discount discount_pkey; Type: CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.discount
    ADD CONSTRAINT discount_pkey PRIMARY KEY (id);


--
-- Name: game game_pkey; Type: CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.game
    ADD CONSTRAINT game_pkey PRIMARY KEY (game_id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);


--
-- Name: _users uk_hchfjvwnaa27i27bfwv0y6n1x; Type: CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._users
    ADD CONSTRAINT uk_hchfjvwnaa27i27bfwv0y6n1x UNIQUE (email);


--
-- Name: cart_items fk51ax78opkvn1tx91cai43why4; Type: FK CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.cart_items
    ADD CONSTRAINT fk51ax78opkvn1tx91cai43why4 FOREIGN KEY (game_game_id) REFERENCES public.game(game_id);


--
-- Name: _order_items fk5lsywaj1fc10tn2kh7nnveptw; Type: FK CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._order_items
    ADD CONSTRAINT fk5lsywaj1fc10tn2kh7nnveptw FOREIGN KEY (game_game_id) REFERENCES public.game(game_id);


--
-- Name: _reviews fk89og1lps30v0738wd76qu31oy; Type: FK CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._reviews
    ADD CONSTRAINT fk89og1lps30v0738wd76qu31oy FOREIGN KEY (user_user_id) REFERENCES public._users(user_id);


--
-- Name: _wishlistitems fk9833wifokhndh2huy2k11toc7; Type: FK CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._wishlistitems
    ADD CONSTRAINT fk9833wifokhndh2huy2k11toc7 FOREIGN KEY (wish_list_wishlist_id) REFERENCES public._wishlists(wishlist_id);


--
-- Name: _wishlistitems fkcpcqir8xvh3go7v0ho0dyb08y; Type: FK CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._wishlistitems
    ADD CONSTRAINT fkcpcqir8xvh3go7v0ho0dyb08y FOREIGN KEY (game_game_id) REFERENCES public.game(game_id);


--
-- Name: cart_items fkd59farg1hfflh4eosvj02wx5j; Type: FK CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.cart_items
    ADD CONSTRAINT fkd59farg1hfflh4eosvj02wx5j FOREIGN KEY (cart_cart_id) REFERENCES public.carts(cart_id);


--
-- Name: orders fkg2fm48xhrua37q3idci07789x; Type: FK CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkg2fm48xhrua37q3idci07789x FOREIGN KEY (user_user_id) REFERENCES public._users(user_id);


--
-- Name: _ticket fkgjik1we9w9vc2orcm56houj7f; Type: FK CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._ticket
    ADD CONSTRAINT fkgjik1we9w9vc2orcm56houj7f FOREIGN KEY (user_id) REFERENCES public._users(user_id);


--
-- Name: game fkgjobvqn0jncwe0w5u56tkdrki; Type: FK CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.game
    ADD CONSTRAINT fkgjobvqn0jncwe0w5u56tkdrki FOREIGN KEY (discount_id) REFERENCES public.discount(id);


--
-- Name: _wishlists fkhygqfd52km397jm3ujh5s2dlh; Type: FK CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._wishlists
    ADD CONSTRAINT fkhygqfd52km397jm3ujh5s2dlh FOREIGN KEY (user_id) REFERENCES public._users(user_id);


--
-- Name: carts fklv4qpicy5iogodu4j841kul5t; Type: FK CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT fklv4qpicy5iogodu4j841kul5t FOREIGN KEY (user_id) REFERENCES public._users(user_id);


--
-- Name: _order_items fkmwf5hohgr1o0n87pvrowpu5tn; Type: FK CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._order_items
    ADD CONSTRAINT fkmwf5hohgr1o0n87pvrowpu5tn FOREIGN KEY (order_order_id) REFERENCES public.orders(order_id);


--
-- Name: discount fkrf2702aal4fw3093eflokoj3f; Type: FK CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public.discount
    ADD CONSTRAINT fkrf2702aal4fw3093eflokoj3f FOREIGN KEY (game_game_id) REFERENCES public.game(game_id);


--
-- Name: _reviews fkser6vf1ffw60nf48syc5u9kgi; Type: FK CONSTRAINT; Schema: public; Owner: sfn
--

ALTER TABLE ONLY public._reviews
    ADD CONSTRAINT fkser6vf1ffw60nf48syc5u9kgi FOREIGN KEY (game_game_id) REFERENCES public.game(game_id);


--
-- PostgreSQL database dump complete
--

