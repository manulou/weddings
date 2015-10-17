--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.3
-- Dumped by pg_dump version 9.4.3
-- Started on 2015-10-17 18:31:20

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 179 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2029 (class 0 OID 0)
-- Dependencies: 179
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 178 (class 1259 OID 16552)
-- Name: country; Type: TABLE; Schema: public; Owner: manul; Tablespace: 
--

CREATE TABLE country (
    id smallint NOT NULL,
    code character varying(2) NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE country OWNER TO manul;

--
-- TOC entry 177 (class 1259 OID 16418)
-- Name: image; Type: TABLE; Schema: public; Owner: manul; Tablespace: 
--

CREATE TABLE image (
    id integer NOT NULL,
    agency_id integer NOT NULL,
    content bytea NOT NULL,
    thumbnail boolean NOT NULL,
    related_image_id integer,
    name character varying(100) NOT NULL,
    content_type character varying(40)
);


ALTER TABLE image OWNER TO manul;

--
-- TOC entry 176 (class 1259 OID 16416)
-- Name: image_id_seq; Type: SEQUENCE; Schema: public; Owner: manul
--

CREATE SEQUENCE image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE image_id_seq OWNER TO manul;

--
-- TOC entry 2030 (class 0 OID 0)
-- Dependencies: 176
-- Name: image_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: manul
--

ALTER SEQUENCE image_id_seq OWNED BY image.id;


--
-- TOC entry 175 (class 1259 OID 16410)
-- Name: user; Type: TABLE; Schema: public; Owner: manul; Tablespace: 
--

CREATE TABLE "user" (
    username character varying(20),
    password character varying(100),
    id integer NOT NULL
);


ALTER TABLE "user" OWNER TO manul;

--
-- TOC entry 174 (class 1259 OID 16408)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: manul
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_id_seq OWNER TO manul;

--
-- TOC entry 2031 (class 0 OID 0)
-- Dependencies: 174
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: manul
--

ALTER SEQUENCE user_id_seq OWNED BY "user".id;


--
-- TOC entry 173 (class 1259 OID 16397)
-- Name: wedding_agency; Type: TABLE; Schema: public; Owner: manul; Tablespace: 
--

CREATE TABLE wedding_agency (
    id integer NOT NULL,
    name character varying(60),
    seolink character varying(60),
    created timestamp with time zone,
    updated timestamp with time zone,
    deleted boolean,
    country_id smallint,
    email character varying(100),
    phone character varying(30),
    min_price integer,
    max_price integer
);


ALTER TABLE wedding_agency OWNER TO manul;

--
-- TOC entry 172 (class 1259 OID 16395)
-- Name: wedding_agency_id_seq; Type: SEQUENCE; Schema: public; Owner: manul
--

CREATE SEQUENCE wedding_agency_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wedding_agency_id_seq OWNER TO manul;

--
-- TOC entry 2032 (class 0 OID 0)
-- Dependencies: 172
-- Name: wedding_agency_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: manul
--

ALTER SEQUENCE wedding_agency_id_seq OWNED BY wedding_agency.id;


--
-- TOC entry 1901 (class 2604 OID 16421)
-- Name: id; Type: DEFAULT; Schema: public; Owner: manul
--

ALTER TABLE ONLY image ALTER COLUMN id SET DEFAULT nextval('image_id_seq'::regclass);


--
-- TOC entry 1900 (class 2604 OID 16413)
-- Name: id; Type: DEFAULT; Schema: public; Owner: manul
--

ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- TOC entry 1899 (class 2604 OID 16400)
-- Name: id; Type: DEFAULT; Schema: public; Owner: manul
--

ALTER TABLE ONLY wedding_agency ALTER COLUMN id SET DEFAULT nextval('wedding_agency_id_seq'::regclass);


--
-- TOC entry 1903 (class 2606 OID 16405)
-- Name: pk_agency; Type: CONSTRAINT; Schema: public; Owner: manul; Tablespace: 
--

ALTER TABLE ONLY wedding_agency
    ADD CONSTRAINT pk_agency PRIMARY KEY (id);


--
-- TOC entry 1909 (class 2606 OID 16556)
-- Name: pk_country; Type: CONSTRAINT; Schema: public; Owner: manul; Tablespace: 
--

ALTER TABLE ONLY country
    ADD CONSTRAINT pk_country PRIMARY KEY (id);


--
-- TOC entry 1907 (class 2606 OID 16426)
-- Name: pk_image; Type: CONSTRAINT; Schema: public; Owner: manul; Tablespace: 
--

ALTER TABLE ONLY image
    ADD CONSTRAINT pk_image PRIMARY KEY (id);


--
-- TOC entry 1905 (class 2606 OID 16415)
-- Name: pk_user; Type: CONSTRAINT; Schema: public; Owner: manul; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT pk_user PRIMARY KEY (id);


--
-- TOC entry 1910 (class 2606 OID 16557)
-- Name: fk_country; Type: FK CONSTRAINT; Schema: public; Owner: manul
--

ALTER TABLE ONLY wedding_agency
    ADD CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES country(id);


--
-- TOC entry 1911 (class 2606 OID 16427)
-- Name: fk_image_agency; Type: FK CONSTRAINT; Schema: public; Owner: manul
--

ALTER TABLE ONLY image
    ADD CONSTRAINT fk_image_agency FOREIGN KEY (agency_id) REFERENCES wedding_agency(id);


--
-- TOC entry 1912 (class 2606 OID 16432)
-- Name: fk_image_related_image; Type: FK CONSTRAINT; Schema: public; Owner: manul
--

ALTER TABLE ONLY image
    ADD CONSTRAINT fk_image_related_image FOREIGN KEY (related_image_id) REFERENCES image(id);


--
-- TOC entry 2028 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-10-17 18:31:20

--
-- PostgreSQL database dump complete
--

