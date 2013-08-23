<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
	<meta name="tipo_contenido"  content="text/html;" http-equiv="content-type" charset="utf-8">

	<tiles:useAttribute id="idPagina" name="idPagina" ignore="true" />	
	<title>
		<tiles:useAttribute id="keyTitulo" name="titulo" />
		<spring:message	code="${keyTitulo}" />
	</title>		
	<script type="text/javascript" src="<c:url value="/js/jQuery/jquery-1.7.2.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jQuery/jquery.form.js"/>"></script>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>	
	<div id="espaceMensaje">
		<div id="slatMensaje">
			<jsp:include page="showMensaje.jsp" />
		</div>
	</div>	
	<c:if test="${!empty idPagina}">
		<c:choose>
			<c:when test="${idPagina == 'login'}">
				<%-- <script type="text/javascript" src="<c:url value="/js/jshash-2.2/sha1-min.js"/>"></script> --%>							
			</c:when>    
			<c:when test="${idPagina == 'formSportCentre'}">
				<script type="text/javascript" src="<c:url value="/js/jshash/sha1-min.js"/>"></script>
				<!-- hosted by Microsoft Ajax CDN -->	
				<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
			</c:when>
			<c:when test="${idPagina == 'formSportTrainer'}">
				<script type="text/javascript" src="<c:url value="/js/jshash/sha1-min.js"/>"></script>
				<!-- <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js"></script>				
				<link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" /> -->
			</c:when> 
			<c:when test="${idPagina == 'formModifySportCentre'}">
				<script type="text/javascript" src="<c:url value="/js/jshash/sha1-min.js"/>"></script>
				<!-- hosted by Microsoft Ajax CDN -->	
				<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
			</c:when>
			<c:when test="${idPagina == 'menuSportCentre'}">
				<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
				<script type="text/javascript" src="<c:url value="/js/jQuery/jnotify/jquery.jnotify.js"/>"></script>
				<link rel="stylesheet" type="text/css" href="<c:url value="/js/jQuery/jnotify/jquery.jnotify.css"/>" />
				<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/ui-lightness/jquery-ui.css" rel="stylesheet" type="text/css" />
				<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
				<script src="<c:url value="/js/jQuery/tableFilter/jquery.uitablefilter.js"/>"></script>
			</c:when>				
			<c:when test="${idPagina == 'menuCustomer'}">
				<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
			</c:when>			
			<c:when test="${idPagina == 'formModifySportTrainer'}">
				<script type="text/javascript" src="<c:url value="/js/jshash/sha1-min.js"/>"></script>
				<!-- hosted by Microsoft Ajax CDN -->	
				<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
			</c:when>	
			<c:when test="${idPagina == 'menuSportTrainer'}">
				<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
				<script type="text/javascript" src="<c:url value="/js/jQuery/jnotify/jquery.jnotify.js"/>"></script>
				<link rel="stylesheet" type="text/css" href="<c:url value="/js/jQuery/jnotify/jquery.jnotify.css"/>" />
				<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/ui-lightness/jquery-ui.css" rel="stylesheet" type="text/css" />
				<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
				<script src="<c:url value="/js/jQuery/tableFilter/jquery.uitablefilter.js"/>"></script>
			</c:when>	  
			<c:when test="${idPagina == 'modifyPassWord'}">
				<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
			</c:when> 
			<c:when test="${idPagina == 'modifyProfile'}">				
				<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
				<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
				<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
				<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
			</c:when>
			<c:when test="${idPagina == 'menuAdmin'}">				
				<script src="<c:url value="/js/jQuery/tableFilter/jquery.uitablefilter.js"/>"></script>
			</c:when>
			<c:when test="${idPagina == 'menuGenerator'}">				
				<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
			</c:when>
			<c:when test="${idPagina == 'menuGeneratorStep1'}">				
				<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
			    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
			    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
			</c:when>
			<c:when test="${idPagina == 'menuGeneratorStep2'}">				
				<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
			    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
			    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
			</c:when>
			<c:when test="${idPagina == 'menuGeneratorStep3'}">				
				<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/themes/ui-lightness/jquery-ui.css" type="text/css" media="all" />				
			    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
			    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
			    <script type="text/javascript" src="<c:url value="/js/jsPDF/jspdf.js"/>"></script>
			    <script type="text/javascript" src="<c:url value="/js/jsPDF/jspdf.plugin.from_html.js"/>"></script>				    
				<script type="text/javascript" src="<c:url value="/js/jsPDF/libs/Deflate/adler32cs.js"/>"></script>
				<script type="text/javascript" src="<c:url value="/js/jsPDF/libs/FileSaver.js/FileSaver.js"/>"></script>
				<script type="text/javascript" src="<c:url value="/js/jsPDF/libs/Blob.js/BlobBuilder.js"/>"></script>	
				<script type="text/javascript" src="<c:url value="/js/jsPDF/jspdf.plugin.standard_fonts_metrics.js"/>"></script>	
				<script type="text/javascript" src="<c:url value="/js/jsPDF/jspdf.plugin.split_text_to_size.js"/>"></script>  
				<script type="text/javascript" src="<c:url value="/js/jsPDF/jspdf.plugin.addimage.js"/>"></script>				
			</c:when>	
			<c:when test="${idPagina == 'menuGeneratorSelectExercieses'}">				
				<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
			    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
			    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
			</c:when>						    			
		</c:choose>
	</c:if>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>" />
	<!-- <script type="text/javascript">
	/*	
	 *	CSSrefresh v1.0.1
	 *	
	 *	Copyright (c) 2012 Fred Heusschen
	 *	www.frebsite.nl
	 *
	 *	Dual licensed under the MIT and GPL licenses.
	 *	http://en.wikipedia.org/wiki/MIT_License
	 *	http://en.wikipedia.org/wiki/GNU_General_Public_License
	 */

	(function() {

		var phpjs = {

			array_filter: function( arr, func )
			{
				var retObj = {}; 
				for ( var k in arr )
				{
					if ( func( arr[ k ] ) )
					{
						retObj[ k ] = arr[ k ];
					}
				}
				return retObj;
			},
			filemtime: function( file )
			{
				var headers = this.get_headers( file, 1 );
				return ( headers && headers[ 'Last-Modified' ] && Date.parse( headers[ 'Last-Modified' ] ) / 1000 ) || false;
		    },
		    get_headers: function( url, format )
		    {
				var req = window.ActiveXObject ? new ActiveXObject( 'Microsoft.XMLHTTP' ) : new XMLHttpRequest();
				if ( !req )
				{
					throw new Error('XMLHttpRequest not supported.');
				}

				var tmp, headers, pair, i, j = 0;

				try
				{
					req.open( 'HEAD', url, false );
					req.send( null ); 
					if ( req.readyState < 3 )
					{
						return false;
					}
					tmp = req.getAllResponseHeaders();
					tmp = tmp.split( '\n' );
					tmp = this.array_filter( tmp, function( value )
					{
						return value.toString().substring( 1 ) !== '';
					});
					headers = format ? {} : [];
		
					for ( i in tmp )
					{
						if ( format )
						{
							pair = tmp[ i ].toString().split( ':' );
							headers[ pair.splice( 0, 1 ) ] = pair.join( ':' ).substring( 1 );
						}
						else
						{
							headers[ j++ ] = tmp[ i ];
						}
					}
		
					return headers;
				}
				catch ( err )
				{
					return false;
				}
			}
		};

		var cssRefresh = function() {

			this.reloadFile = function( links )
			{
				for ( var a = 0, l = links.length; a < l; a++ )
				{
					var link = links[ a ],
						newTime = phpjs.filemtime( this.getRandom( link.href ) );

					//	has been checked before
					if ( link.last )
					{
						//	has been changed
						if ( link.last != newTime )
						{
							//	reload
							link.elem.setAttribute( 'href', this.getRandom( link.href ) );
						}
					}

					//	set last time checked
					link.last = newTime;
				}
				setTimeout( function()
				{
					this.reloadFile( links );
				}, 1000 );
			};

			this.getHref = function( f )
			{
				return f.getAttribute( 'href' ).split( '?' )[ 0 ];
			};
			this.getRandom = function( f )
			{
				return f + '?x=' + Math.random();
			};


			var files = document.getElementsByTagName( 'link' ),
				links = [];

			for ( var a = 0, l = files.length; a < l; a++ )
			{			
				var elem = files[ a ],
					rel = elem.rel;
				if ( typeof rel != 'string' || rel.length == 0 || rel == 'stylesheet' )
				{
					links.push({
						'elem' : elem,
						'href' : this.getHref( elem ),
						'last' : false
					});
				}
			}
			this.reloadFile( links );
		};


		cssRefresh();

	})();
	</script> -->