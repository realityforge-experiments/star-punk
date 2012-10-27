package starpunk.util;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A simple implementation of an LRU cache. Retrieved from <a href=
 * "http://stackoverflow.com/questions/224868/easy-simple-to-use-lru-cache-in-java"
 * >Stackoverflow</a>.
 */
public final class LRUCache<K, V>
{
  private final Map<K, V> _cache;
  private CacheEntryRemovedListener<K, V> _entryRemovedListener;

  /** Creates the cache with the specified max entries. */
  public LRUCache( final int maxEntries )
  {
    _cache = new LinkedHashMap<K, V>( maxEntries + 1, .75F, true )
    {
      public boolean removeEldestEntry( final Map.Entry<K, V> eldest )
      {
        if( size() > maxEntries )
        {
          if( null != _entryRemovedListener )
          {
            _entryRemovedListener.notifyEntryRemoved( eldest.getKey(), eldest.getValue() );
          }
          return true;
        }
        else
        {
          return false;
        }
      }
    };
  }

  public void add( final K key, final V value )
  {
    _cache.put( key, value );
  }

  public V get( final K key )
  {
    return _cache.get( key );
  }

  public Collection<V> retrieveAll()
  {
    return _cache.values();
  }

  public void setEntryRemovedListener( final CacheEntryRemovedListener<K, V> entryRemovedListener )
  {
    _entryRemovedListener = entryRemovedListener;
  }
}
