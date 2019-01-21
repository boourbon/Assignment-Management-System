
var Af = {};

Af.rest = function (URI, ARGS, SUCCESS_CALLBACK, ERROR_CALLBACK)
{
	jQuery.ajax({				
		url: URI, // <--			
		method: "POST", 
		processData: false,	
		data: JSON.stringify(ARGS), // <--
		success: function(data, textStatus, jqXHR){
			SUCCESS_CALLBACK (JSON.parse(data) ); // <--
		},
		error: function( jqXHR, textStatus, errorThrown){
			if(typeof ERROR_CALLBACK != "undefined" && ERROR_CALLBACK != null) 
				ERROR_CALLBACK(errorThrown);
			else
			{
				if(errorThrown.length>0) alert( "error: " + errorThrown );	
			}
		}
	});	
}

Af.nullstr = function ( v )
{
	return v == null || v.length==0 ;
}


Af.trace = function(msg)
{
	 try {   console.log(msg);     } catch (err) {}
};


function AfTag (name, pair)
{
	this.name = name;
	this.attrList = {};
	this.innerHtml = "";
	this.pair = pair;	
	
	this.a = function(attrName, attrValue)
	{
		this.attrList[attrName] = attrValue;
		return this;
	};
	this.aa = function(attrName, attrValue)
	{
		if(this.attrList[attrName] == null)
			this.attrList[attrName] = attrValue;
		else
			this.attrList[attrName] += (" " + attrValue);
		return this;
	};
	//
	this.t = function ( innerHtml)
	{
		this.innerHtml = innerHtml;
		return this;
	};
	this.tt = function ( innerHtml)
	{		
		this.innerHtml += innerHtml;
		return this;
	};

	this.toHtml = function()
	{
		var htmlAttr = "";
		for(attrName in this.attrList)
		{
			var attrValue = this.attrList[attrName];
			var str = " " + attrName + "='" + attrValue + "'"  + " ";
			htmlAttr += str;
		}
		
		if(false == this.pair)
		{
			return  "<" + name + htmlAttr + "/>" // <img />
	 				;
		}
		else
		{
			return  "<" + name + htmlAttr + ">" // <div>
 				+ this.innerHtml
 				+ "</" + name + ">" // </div>
 				;
		}
	}; 		
}

function AfIdList ()
{
	this.ids = [];	
	
	this.aa = function (str)
	{
		if(str==null || str.length==0) return this;
		var sss = str.split(",");
		for(var i=0; i<sss.length; i++)
		{
			var it = sss[i];
			if(it.length > 0 && ! this.contains( it ))
			{				
				this.ids.push(it);
			}
		}
		return this;
	};
	this.at = function(index)
	{
		if(this.ids.length == 0) return null;
		return this.ids[index];
	};
	this.contains = function (id)
	{
		for(var i=0; i<this.ids.length; i++)
		{
			if( id == this.ids[i]) return true;
		}
		return false;
	};
	this.size = function ()
	{
		return this.ids.length;
	};
	this.toString = function()
	{
		return this.ids.join(",");
	}
}

function AfMap()
{
	this.array = [];
	
	this.put = function(id, obj)
	{
		for(var i=0; i<this.array.length;i++)
		{
			var e = this.array[i];
			if(e.id == id) 
			{
				e.obj = obj;
				return;
			}
		}

		var e = {};
		e.id = id;
		e.obj = obj;
		this.array.push( e );
	};
	
	this.get = function(id)
	{
		for(var i=0; i<this.array.length;i++)
		{
			var e = this.array[i];
			if(e.id == id) 
				return e.obj;
		}
		return null;
	};
	
	this.each = function ( callback )
	{
		for(var i=0; i<this.array.length;i++)
		{
			var e = this.array[i];
			if(false == callback (e.id, e.obj ) ) break;
		}
	};
	
	this.remove = function ( id )
	{
		for(var i=0; i<this.array.length;i++)
		{
			var e = this.array[i];
			if(e.id == id)
				this.array.splice(i, 1);
		}
	};
	
	this.size = function()
	{
		return this.array.length;
	};
	
	this.clear = function()
	{
		this.array = [];
	};
	
	this.values = function()
	{
		var values = [];
		for(var i=0; i<this.array.length;i++)
		{
			var e = this.array[i];
			values.push(e.obj);
		}
		return values;
	};
}

Af.indexOf = function(value, array)
{
	for(var i=0; i<array.length; i++)
	{
		if(array[i] == value) return i;
	}
	return -1;
};
Af.indexOf2 = function(value, strlist)
{
	var array = strlist.split(",");
	var v1 = value + "";
	for(var i=0; i<array.length; i++)
	{
		var v2 = array[i] + "";
		if(v1 == v2) return i;
	}
	return -1;
};

Af.shortName = function(title, N)
{
	if(title.length <= N) 
		return title;
	else 
		return title.substr(0,N) + "...";
};

Af.set_options = function( container, options)
{
	var boxes = $("[type='checkbox']", container);
	boxes.prop("checked",false);
	
	if(options==null) return;
	//var sss = options.split(",");
	
	var boxes = $("[type='checkbox']", container);
	for( var i=0; i<boxes.length; i++)
	{
		var box = $( boxes[i] );
		var id = box.attr("id1");
		if(Af.indexOf(id, options) >= 0)
		{
			box.prop("checked", true);
		}
	}
};	

Af.get_options = function( container)
{
	var options = [];
	var boxes = $("[type='checkbox']", container);
	for( var i=0; i<boxes.length; i++)
	{
		var box = $( boxes[i] );			
		if( box.prop("checked"))
		{
			var id = box.attr("id1");
			options.push (id);
		}
	}
	return options;
};	

Af.suffix = function( fileName)
{
	var index = fileName.lastIndexOf(".");
	if(index < 0) return "";
	return fileName.substr(index+1).toLowerCase();
};

Af.get_local = function(item, dftValue)
{
	var value = localStorage.getItem(item);
	if(item == null) return dftValue;
	return value;
};

Af.set_local = function(item, value)
{
	localStorage.setItem(item, value);	
};

Af.blank = function(check, value)
{
	return check ? value : "";
}
