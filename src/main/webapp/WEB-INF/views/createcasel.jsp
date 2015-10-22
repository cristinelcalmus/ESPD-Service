<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="container espd-container">

	<div class="panel-default">
	
		<div class="panel panel-default no-border">	
			<ul class="nav nav-pills nav-wizard nav-justified">
			    <li class="active"><a href="#"><i class="fa fa-random"></i>&nbsp;<span data-i18n="progress_start"><s:message code='progress_start'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-university"></i>&nbsp;<span data-i18n="progress_procedure"><s:message code='progress_procedure'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-exclamation"></i>&nbsp;<span data-i18n="progress_exclusion"><s:message code='progress_exclusion'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-check-circle"></i>&nbsp;<span data-i18n="progress_selection"><s:message code='progress_selection'/></span></a><div class="nav-arrow"></div></li>
			    <li><div class="nav-wedge"></div><a href="#"><i class="fa fa-download"></i>&nbsp;<span data-i18n="progress_finish"><s:message code='progress_finish'/></span></a></li>
			</ul>
		</div>

		<div class="paragraph">
			<h2 data-i18n="createcasel_header"><s:message code='createcasel_header'/></h2>
		</div>
		
		<div class="alert alert-espd-info">
			<ul class="fa-ul">
			<li>
				<i class="info-label fa fa-info-circle fa-lg fa-li"></i>
				<span data-i18n="createcasel_alert"><s:message code='createcasel_alert'/></span>
			</li>
			</ul>
		</div>
				
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcasel_SUITABILITY" class="espd-panel-heading">
				<s:message code='createcasel_SUITABILITY'/>
			</div>
			<div class="panel-body">
				...
			</div>
		</div>
	
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcasel_ECONOMIC_AND_FINSTADING" class="espd-panel-heading">
				<s:message code='createcasel_ECONOMIC_AND_FINSTADING'/>
			</div>
			<div class="panel-body">
				...
			</div>
		</div>
	
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcasel_grounds_insolvency" class="espd-panel-heading">
				<s:message code='createcasel_grounds_insolvency'/>
			</div>
			<div class="panel-body">
				...
			</div>
		</div>
	
		<div class="col-md-7">&nbsp;</div>
		<div class="col-md-5">
			<div class="btn-group">
				<a class="btn btn-default btn-lg" href="/espd/createcaexcl">
				<i class="fa fa-arrow-circle-o-left"></i>
				<span data-i18n="PREV"><s:message code="PREV"/></span>
				</a>
				<a class="btn btn-default btn-lg" href="/espd/welcome">
				<i class="fa fa-times-circle"></i>
				<span data-i18n="CANCEL"><s:message code="CANCEL"/></span>
				</a>
				<a class="btn btn-default btn-lg" href="/espd/createcafinish">
				<i class="fa fa-arrow-circle-o-right"></i>
				<span data-i18n="NEXT"><s:message code="NEXT"/></span>
				</a>
			</div>
		</div>
	</div>
</div>
